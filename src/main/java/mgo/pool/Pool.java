package mgo.pool;

import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Pool {
	private Random rnd = new Random();

	public int id;
	public String name;
	public JSONArray groups;
	public int[] weightArray;

	public Card drawOne() {
		int groupRnd = rnd.nextInt(weightArray[weightArray.length - 1]) + 1;
		int groupIndex = 0;
		for (int i = 0; i < weightArray.length; i++) {
			if (groupRnd <= weightArray[i]) {
				groupIndex = i;
				break;
			}
		}
		JSONObject group = groups.getJSONObject(groupIndex);
		int cardIndex = rnd.nextInt(group.getJSONArray("group").size());
		return new Card(group.getString("type"), group.getJSONArray("group").getIntValue(cardIndex));
	};

	public Card[] drawTen() {
		Card[] result = new Card[10];
		for (int i = 0; i < 10; i++) {
			result[i] = drawOne();
		}
		return result;
	};

	public Pool(JSONObject data) {
		id = data.getIntValue("id");
		name = data.getString("name");
		groups = data.getJSONArray("groups");

		weightArray = new int[groups.size()];
		int total = 0;
		for (int i = 0; i < groups.size(); i++) {
			int weight = groups.getJSONObject(i).getIntValue("weight");
			total += weight;
			weightArray[i] = total;
		}
	};

	public class Card {
		private String type;
		private int id;

		public Card(String type, int id) {
			this.type = type;
			this.id = id;
		}

		@Override
		public String toString() {
			return "type:" + type + " id:" + id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}