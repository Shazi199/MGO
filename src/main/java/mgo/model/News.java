package mgo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import mgo.model.base.BaseNews;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class News extends BaseNews<News> {
	public List<News> findLatestNews() {
		Page<News> result = paginate(1, 10, getSqlPara("findAllNewsForPagination"));
		return result.getList();
	}
	
	public int getLatestNewsCount() {
		return Db.queryInt(getSql("getLatestNewsCount"));
	}

	public String findNewsContent(long id) {
		News news = findById(id);
		return news == null ? null : news.getContent();
	}
}
