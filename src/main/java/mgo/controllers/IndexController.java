package mgo.controllers;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import mgo.interceptors.CheckLoginInterceptor;

public class IndexController extends Controller {
	
	@Clear(CheckLoginInterceptor.class)
	public void index() {
		setAttr("weibo_appid", PropKit.get("weibo_appid"));
		setAttr("weibo_url", PropKit.get("weibo_url"));
		setAttr("captcha_enable", PropKit.getBoolean("captcha_enable"));
		renderTemplate("index.html");
	}

	@Clear(CheckLoginInterceptor.class)
	public void captcha() {
		renderCaptcha();
	}

	@Clear(CheckLoginInterceptor.class)
	public void reg() {
		renderTemplate("reg.html");
	}
}
