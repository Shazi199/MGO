package mgo.controllers;

import java.util.List;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import mgo.model.Craft;
import mgo.model.Servent;
import mgo.model.User;
import mgo.service.CraftService;
import mgo.service.ServentService;

public class DetailController extends Controller {
    
    @Inject
    private CraftService craftService;
    
    @Inject
    private ServentService serventService;
    
	public void index() {
		renderTemplate("index.html");
	}

	public void loadServent() {
		User u = getSessionAttr("currentUser");

		List<Servent> datas = serventService.findServentsByUserid(u.getId());
		setAttr("datas", datas);
		
		renderJson(datas);
	}

	public void loadCraft() {
		User u = getSessionAttr("currentUser");

		List<Craft> datas = craftService.findCraftsByUserid(u.getId());
		setAttr("datas", datas);
		
		renderJson(datas);
	}
}
