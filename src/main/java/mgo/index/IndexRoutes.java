package mgo.index;

import com.jfinal.config.Routes;

public class IndexRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view/index");
		add("/", IndexController.class);
	}

}
