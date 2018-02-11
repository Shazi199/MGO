package mgo.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import mgo.interceptors.ManageAuthInterceptor;

@Before(ManageAuthInterceptor.class)
public class ManageController extends Controller {

}
