package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.IndexController;

public class IndexRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view/index");
		add("/", IndexController.class);
	}

}
