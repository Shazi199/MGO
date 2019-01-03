package tool;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

public class ModelGenerator {
	public static void main(String[] args) {
		PropKit.use("config.txt");
		DruidPlugin plugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		plugin.start();
		Generator gen = new Generator(plugin.getDataSource(), "mgo.model.base", "src/main/java/mgo/model/base/", "mgo.model", "src/main/java/mgo/model/");
		gen.addExcludedTable("db_version");
		gen.generate();
	}
}
