package tool;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class ModelGenerator {
	public static void main(String[] args) {
		PropKit.use("config.txt");
		C3p0Plugin plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		plugin.start();
		Generator gen = new Generator(plugin.getDataSource(), "mgo.model", "src/main/java/mgo/model/", "mgo.model", "src/main/java/mgo/model/");
		gen.generate();
	}
}
