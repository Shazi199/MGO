package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.TeamController;

public class TeamRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/team", TeamController.class);
	}

}
