package mgo.account;

import com.jfinal.config.Routes;

public class AccountRoutes extends Routes {

	@Override
	public void config() {
		add("/account", AccountController.class);
	}

}
