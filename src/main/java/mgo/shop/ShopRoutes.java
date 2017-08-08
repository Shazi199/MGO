package mgo.shop;

import com.jfinal.config.Routes;

public class ShopRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/shop", ShopController.class);
	}

}
