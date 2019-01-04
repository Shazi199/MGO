package mgo.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import mgo.model.News;

public class NewsService {
    public final News dao = new News().dao();

    public List<News> findLatestNews() {
        Page<News> result = dao.paginate(1, 10, dao.getSqlPara("findAllNewsForPagination"));
        return result.getList();
    }

    public int getLatestNewsCount() {
        return Db.queryInt(dao.getSql("getLatestNewsCount"));
    }

    public String findNewsContent(long id) {
        News news = dao.findById(id);
        return news == null ? null : news.getContent();
    }
}
