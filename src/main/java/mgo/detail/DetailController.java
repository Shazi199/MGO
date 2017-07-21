package mgo.detail;

import com.jfinal.core.Controller;

public class DetailController extends Controller {
	public void index() {
		renderTemplate("index.html");
	}
	
	public void loadServent() {
		renderTemplate("servent.html");
	}
	
	public void loadCraft() {
		renderTemplate("craft.html");
	}
}
