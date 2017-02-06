package mgo.summon;

import com.jfinal.core.Controller;

public class SummonController extends Controller {
	public void index() {
		renderTemplate("index.html");
	}
}
