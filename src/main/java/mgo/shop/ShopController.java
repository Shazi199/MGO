package mgo.shop;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.tx.Tx;

import mgo.model.User;

public class ShopController extends Controller {
	public void index() {
		renderTemplate("index.html");
	}

	@Before(Tx.class)
	public void buy140() {
		User u = getSessionAttr("currentUser");
		u.setStone(u.getStone() + 140);
		u.setCost(u.getCost() + 518);
		u.update();
		
		renderJson(JMap.ok("msg", "购买成功！"));
	}
}
