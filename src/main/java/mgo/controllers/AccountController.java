package mgo.controllers;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.tx.Tx;

import mgo.interceptors.CheckLoginInterceptor;
import mgo.model.Oauth;
import mgo.model.User;
import mgo.service.OauthService;
import mgo.service.UserService;
import mgo.util.Message;

public class AccountController extends Controller {
    
    @Inject
    private OauthService oauthService;
    @Inject
    private UserService userService;

	@Clear(CheckLoginInterceptor.class)
	public void login() {
		if (PropKit.getBoolean("captcha_enable") && !validateCaptcha("randomCode")) {
			renderJson(Message.fail("验证码错误"));
			return;
		}
		String username = getPara("username");
		String userpass = getPara("userpass");

		User u = userService.findByUsername(username);
		if (u != null && u.getUserpass().equals(userpass)) {
			u.setLastlogin(new Date());
			u.update();
			setSessionAttr("currentUser", u);
			renderJson(Message.ok());
		} else {
			renderJson(Message.fail("登陆失败请重试"));
		}
	}

	@Clear(CheckLoginInterceptor.class)
	public void logout() {
		removeSessionAttr("currentUser");
		getSession().invalidate();
		renderJson("result", "ok");
	}

	@Clear(CheckLoginInterceptor.class)
	public void reg() {
		if (!validateCaptcha("randomCode")) {
			renderJson(Message.fail("验证码错误"));
			return;
		}
		String username = getPara("username");
		String userpass = getPara("userpass");
		String email = getPara("email");
		String nickname = getPara("nickname");

		User u = userService.findByUsername(username);
		if (u != null) {
			renderJson(Message.fail("用户名已存在"));
			return;
		}
		
		u = new User();
		u.setUsername(username);
		u.setUserpass(userpass);
		u.setEmail(email);
		u.setNickname(nickname);
		u.setStone(0);
		u.setCost(0);
		u.setCreated(new Date());
		u.setLastlogin(new Date());
		u.save();
		
		renderJson("result", "ok");
	}

	@SuppressWarnings("unchecked")
	public void getStone() {
		User u = getSessionAttr("currentUser");
		Kv kv = Message.ok();
		kv.put("stone", u.getStone());
		renderJson(kv);
	}

	@Clear(CheckLoginInterceptor.class)
	public void weiboLogin() {
		String code = getPara("code");
		String url = "https://api.weibo.com/oauth2/access_token?client_id=" + PropKit.get("weibo_appid")
		+ "&client_secret=" + PropKit.get("weibo_secretid") + "&grant_type=authorization_code"
		+ "&redirect_uri=http://" + PropKit.get("weibo_url") + "/account/weiboLogin" + "&code=" + code;

		String content = HttpKit.post(url, null);

		JSONObject object = JSONObject.parseObject(content);
		String uid = object.getString("uid");
		User u = userService.findByOauth(uid, "weibo");

		if (u != null) {
			u.setLastlogin(new Date());
			u.update();
			setSessionAttr("currentUser", u);

			redirect("/");
		} else {
			renderText("未关联有效账户");
		}
	}
	
	@Before(Tx.class)
	public void weiboBinding() {
		User u = getSessionAttr("currentUser");
		
		String code = getPara("code");
		String url = "https://api.weibo.com/oauth2/access_token?client_id=" + PropKit.get("weibo_appid")
		+ "&client_secret=" + PropKit.get("weibo_secretid") + "&grant_type=authorization_code"
		+ "&redirect_uri=http://" + PropKit.get("weibo_url") + "/account/weiboLogin" + "&code=" + code;

		String content = HttpKit.post(url, null);

		JSONObject object = JSONObject.parseObject(content);
		String uid = object.getString("uid");
		String token = object.getString("access_token");
		Oauth oauth = oauthService.findOauthByType(u.getId(), "weibo");
		if (oauth == null) {
			oauth = new Oauth();
			oauth.setAuthid(uid);
			oauth.setUid(u.getId());
			oauth.setType("weibo");
			oauth.setToken(token);
			oauth.save();
		} else {
			oauth.setAuthid(uid);
			oauth.setToken(token);
			oauth.update();
		}

		renderText("绑定成功！");
	}
}
