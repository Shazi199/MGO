package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.SummonController;

public class SummonRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/summon", SummonController.class);
	}

}
