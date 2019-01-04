package mgo.controllers;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;

import mgo.model.News;
import mgo.service.NewsService;
import mgo.util.Message;

public class NewsController extends Controller {
    
    @Inject
    private NewsService newsService;
    
	public void index() {
		List<News> list = newsService.findLatestNews();
		setAttr("list", list);
		renderTemplate("index.html");
	}

	public void getNewsDetail() {
		long id = getParaToLong("id");
		News news = new News().findById(id);
		renderJson(news);
	}

	@Before(CacheInterceptor.class)
	public void getLatestNewsCount() {
		int count = newsService.getLatestNewsCount();
		renderJson(Message.ok(count == 0 ? "" : String.valueOf(count)));
	}
}
