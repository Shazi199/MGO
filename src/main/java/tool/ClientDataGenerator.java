package tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Const;

import mgo.config.Data;
import mgo.util.DataLoader;

public class ClientDataGenerator {
	public static void main(String[] args) {
		Data.instance.init();
		Map<Integer, JSONObject> serventData = Data.instance.serventData;
		StringBuilder db = new StringBuilder();
		db.append("var SERVENT_DB=");
		db.append(JSON.toJSONString(serventData));
		db.append(";\r\n");
		Map<Integer, JSONObject> craftData = Data.instance.craftData;
		db.append("var CRAFT_DB=");
		db.append(JSON.toJSONString(craftData));
		db.append(";\r\n");
		JSONObject classData = DataLoader.loadObjectData("class.txt");
		db.append("var CLASS_DB=");
		db.append(JSON.toJSONString(classData));
		db.append(";\r\n");
		db.append("var DATA_DB={\"servent\":SERVENT_DB,\"craft\":CRAFT_DB,\"class\":CLASS_DB};");
		File dbJS = new File("src/main/webapp/js/db.js");
		if (dbJS.exists()) {
			dbJS.delete();
		}
		try {
			dbJS.createNewFile();
			FileOutputStream fos = new FileOutputStream(dbJS);
			IOUtils.write(db, fos, Const.DEFAULT_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Generated Client Data: " + dbJS.getAbsolutePath());
	}
}
