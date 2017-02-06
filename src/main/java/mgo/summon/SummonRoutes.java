package mgo.summon;

import com.jfinal.config.Routes;

public class SummonRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("_view");
		add("/summon", SummonController.class);
	}

}
