package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.DetailController;

public class DetailRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/detail", DetailController.class);
	}

}
