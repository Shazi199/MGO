package mgo.routes;

import com.jfinal.config.Routes;

import mgo.controllers.NewsController;

public class NewsRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/news", NewsController.class);
	}

}
