package mgo.detail;

import com.jfinal.config.Routes;

public class DetailRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/detail", DetailController.class);
	}

}
