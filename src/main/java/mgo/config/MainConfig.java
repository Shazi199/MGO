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
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;

import mgo.account.AccountRoutes;
import mgo.account.CheckLoginInterceptor;
import mgo.common.GlobalAttributeInterceptor;
import mgo.index.IndexRoutes;
import mgo.model._MappingKit;
import mgo.summon.SummonRoutes;

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
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		me.add(c3p0Plugin);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);
		_MappingKit.mapping(activeRecordPlugin);
		activeRecordPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		activeRecordPlugin.addSqlTemplate("user.sql");
		me.add(activeRecordPlugin);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new CheckLoginInterceptor());
		me.addGlobalActionInterceptor(new GlobalAttributeInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

}
