package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.SettingController;

public class SettingRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/setting", SettingController.class);
	}

}
