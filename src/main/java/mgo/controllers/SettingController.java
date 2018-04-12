package mgo.controllers;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class SettingController extends Controller {
	public void index() {
		setAttr("weibo_appid", PropKit.get("weibo_appid"));
		setAttr("weibo_url", PropKit.get("weibo_url"));
		setAttr("admin_username", PropKit.get("admin_username"));
		renderTemplate("index.html");
	}
}
