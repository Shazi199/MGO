package mgo.setting;

import com.jfinal.config.Routes;

public class SettingRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/setting", SettingController.class);
	}

}
