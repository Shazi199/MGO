package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.ManageController;

public class ManageRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/manage", ManageController.class);
	}

}
