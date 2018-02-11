package mgo.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import mgo.model.User;

public class ManageAuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		User u = controller.getSessionAttr("currentUser");
		if (u != null && PropKit.get("admin_username").equals(u.getUsername())) {
			inv.invoke();
		} else {
			controller.redirect("/");
		}
	}

}
