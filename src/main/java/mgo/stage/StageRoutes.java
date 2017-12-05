package mgo.stage;

import com.jfinal.config.Routes;

public class StageRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/stage", StageController.class);
		add("/battle", BattleController.class);
	}

}
