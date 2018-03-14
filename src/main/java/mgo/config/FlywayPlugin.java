package mgo.config;

import org.flywaydb.core.Flyway;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;

public class FlywayPlugin implements IPlugin {

	private IDataSourceProvider dataSourceProvider;

	public FlywayPlugin(IDataSourceProvider dataSourceProvider) {
		this.dataSourceProvider = dataSourceProvider;
	}

	@Override
	public boolean start() {
		try {
			Flyway flyway = new Flyway();
			flyway.setTable("db_version");
			flyway.setDataSource(this.dataSourceProvider.getDataSource());
			flyway.migrate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean stop() {
		this.dataSourceProvider = null;
		return true;
	}

}
