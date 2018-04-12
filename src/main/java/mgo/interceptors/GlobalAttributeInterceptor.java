package mgo.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class GlobalAttributeInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		controller.setAttr("currentUser", controller.getSessionAttr("currentUser"));
		controller.setAttr("homeurl", PropKit.get("homeurl"));
		inv.invoke();
	}

}
