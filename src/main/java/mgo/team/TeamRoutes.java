package mgo.team;

import com.jfinal.config.Routes;

public class TeamRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/team", TeamController.class);
	}

}
