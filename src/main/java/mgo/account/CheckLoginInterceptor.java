package mgo.account;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import mgo.model.User;

public class CheckLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		User u = controller.getSessionAttr("currentUser");
		if (u == null) {
			controller.redirect("/");
		} else {
			inv.invoke();
		}
	}

}
