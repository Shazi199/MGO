package mgo.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;

import mgo.interceptors.CheckLoginInterceptor;
import mgo.interceptors.GlobalAttributeInterceptor;
import mgo.model._MappingKit;
import mgo.routes.AccountRoutes;
import mgo.routes.DetailRoutes;
import mgo.routes.IndexRoutes;
import mgo.routes.ManageRoutes;
import mgo.routes.NewsRoutes;
import mgo.routes.SettingRoutes;
import mgo.routes.ShopRoutes;
import mgo.routes.StageRoutes;
import mgo.routes.SummonRoutes;
import mgo.routes.TeamRoutes;

public class MainConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode"));
		Data.instance.init();
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new IndexRoutes());
		me.add(new AccountRoutes());
		me.add(new SummonRoutes());
		me.add(new ShopRoutes());
		me.add(new DetailRoutes());
		me.add(new TeamRoutes());
		me.add(new NewsRoutes());
		me.add(new SettingRoutes());
		me.add(new StageRoutes());
		me.add(new ManageRoutes());
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {
		me.add(new EhCachePlugin());

		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		druidPlugin.setFilters("stat,wall");
		me.add(druidPlugin);

		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		_MappingKit.mapping(activeRecordPlugin);
		activeRecordPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		activeRecordPlugin.addSqlTemplate("user.sql");
		activeRecordPlugin.addSqlTemplate("servent.sql");
		activeRecordPlugin.addSqlTemplate("craft.sql");
		activeRecordPlugin.addSqlTemplate("teamMember.sql");
		activeRecordPlugin.addSqlTemplate("team.sql");
		activeRecordPlugin.addSqlTemplate("news.sql");
		activeRecordPlugin.addSqlTemplate("oauth.sql");
		activeRecordPlugin.addSqlTemplate("stage.sql");
		me.add(activeRecordPlugin);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new CheckLoginInterceptor());
		me.addGlobalActionInterceptor(new GlobalAttributeInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		DruidStatViewHandler druidStatViewHandler = new DruidStatViewHandler("/manage/dbstat", new DruidStatViewAuth());
		me.add(druidStatViewHandler);
	}

}
