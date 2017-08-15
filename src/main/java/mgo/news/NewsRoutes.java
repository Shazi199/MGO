package mgo.news;

import com.jfinal.config.Routes;

public class NewsRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/news", NewsController.class);
	}

}
