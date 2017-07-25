package mgo.account;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.JMap;
import com.jfinal.kit.PropKit;

import mgo.model.User;

public class AccountController extends Controller {

	@Clear(CheckLoginInterceptor.class)
	public void login() {
		String username = getPara("username");
		String userpass = getPara("userpass");

		User u = new User().findByUsername(username);
		u.setLastlogin(new Date());
		u.update();
		if (u != null && u.getUserpass().equals(userpass)) {
			setSessionAttr("currentUser", u);
			renderJson("result", "ok");
		} else {
			renderJson("result", "fail");
		}
	}

	@Clear(CheckLoginInterceptor.class)
	public void logout() {
		removeSessionAttr("currentUser");
		renderJson("result", "ok");
	}

	public void getStone() {
		User u = getSessionAttr("currentUser");

		renderJson(JMap.ok("stone", u.getStone()));
	}

	@Clear(CheckLoginInterceptor.class)
	public void weiboLogin() {
		String code = getPara("code");

		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weibo.com/oauth2/access_token?client_id=" + PropKit.get("weibo_appid")
				+ "&client_secret=" + PropKit.get("weibo_secretid") + "&grant_type=authorization_code"
				+ "&redirect_uri=http://" + PropKit.get("weibo_url") + "/account/weiboLogin" + "&code=" + code);
		try {
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);

			JSONObject object = JSONObject.parseObject(content);
			String uid = object.getString("uid");
			User u = new User().findByOauth(uid, "weibo");

			if (u != null) {
				u.setLastlogin(new Date());
				u.update();
				setSessionAttr("currentUser", u);

				redirect("/");
			} else {
				renderText("未关联有效账户");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
