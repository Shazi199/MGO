package mgo.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class GlobalAttributeInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		controller.setAttr("currentUser", controller.getSessionAttr("currentUser"));
		inv.invoke();
	}

}
