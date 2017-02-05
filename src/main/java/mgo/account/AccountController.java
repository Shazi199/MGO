package mgo.account;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import mgo.model.User;

public class AccountController extends Controller {
	
	@Clear(CheckLoginInterceptor.class)
	public void login() {
		String username = getPara("username");
		String userpass = getPara("userpass");

		User u = new User().findByUsername(username);
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
}
