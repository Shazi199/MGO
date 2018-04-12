package mgo.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import mgo.interceptors.ManageAuthInterceptor;

@Before(ManageAuthInterceptor.class)
public class ManageController extends Controller {
	public void index() {
		renderTemplate("index.html");
	}
	
	public void stage() {
		renderTemplate("user.html");
	}
	
	public void user() {
		renderTemplate("user.html");
	}
	
	public void news() {
		renderTemplate("user.html");
	}
	
	public void setting() {
		renderTemplate("user.html");
	}
}
