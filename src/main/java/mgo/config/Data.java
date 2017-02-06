package mgo.config;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import mgo.util.DataLoader;

public class Data {
	public Map<Integer, JSONObject> serventData;
	public Map<Integer, JSONObject> craftData;
	public Map<Integer, JSONObject> poolData;

	public static final Data instance = new Data();

	private Data() {
	}

	public void init() {
		serventData = generateIDMap(DataLoader.loadArrayData("servent.txt"));
		craftData = generateIDMap(DataLoader.loadArrayData("craft.txt"));
		poolData = generateIDMap(DataLoader.loadArrayData("pool.txt"));
	}

	private Map<Integer, JSONObject> generateIDMap(JSONArray data) {
		Map<Integer, JSONObject> result = new HashMap<Integer, JSONObject>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject obj = data.getJSONObject(i);
			result.put(obj.getInteger("id"), obj);
		}
		return result;
	}
}
