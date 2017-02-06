package mgo.index;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import mgo.account.CheckLoginInterceptor;

public class IndexController extends Controller {
	
	@Clear(CheckLoginInterceptor.class)
	public void index() {
		renderTemplate("index.html");
	}
}
