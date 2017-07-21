package mgo.account;

import java.util.Date;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.JMap;

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
}
