package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.ShopController;

public class ShopRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/shop", ShopController.class);
	}

}
