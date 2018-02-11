package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.BattleController;
import mgo.controllers.StageController;

public class StageRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/stage", StageController.class);
		add("/battle", BattleController.class);
	}

}
