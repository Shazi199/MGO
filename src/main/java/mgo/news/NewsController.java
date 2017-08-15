package mgo.news;

import java.util.List;

import com.jfinal.core.Controller;

import mgo.model.News;

public class NewsController extends Controller {
	public void index() {
		List<News> list = new News().findLatestNews();
		setAttr("list", list);
		renderTemplate("index.html");
	}

	public void getNewsDetail() {
		long id = getParaToLong("id");
		News news = new News().findById(id);
		renderJson(news);
	}
}
