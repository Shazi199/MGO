package mgo.detail;

import java.util.List;

import com.jfinal.core.Controller;

import mgo.model.Craft;
import mgo.model.Servent;
import mgo.model.User;

public class DetailController extends Controller {
	public void index() {
		renderTemplate("index.html");
	}

	public void loadServent() {
		User u = getSessionAttr("currentUser");

		List<Servent> datas = new Servent().findServentsByUserid(u.getId());
		setAttr("datas", datas);
		
		renderJson(datas);
	}

	public void loadCraft() {
		User u = getSessionAttr("currentUser");

		List<Craft> datas = new Craft().findCraftsByUserid(u.getId());
		setAttr("datas", datas);
		
		renderJson(datas);
	}
}
