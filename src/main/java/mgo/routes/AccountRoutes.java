package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.AccountController;

public class AccountRoutes extends Routes {

	@Override
	public void config() {
		add("/account", AccountController.class);
	}

}
