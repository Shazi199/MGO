package mgo.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import mgo.config.Data;
import mgo.model.Servent;

public class Battle {
	public List<FriendUnit> players = new ArrayList<FriendUnit>();
	public List<EnemyUnit> enemies = new ArrayList<EnemyUnit>();

	public List<Card> currentCards = new ArrayList<Card>();
	public List<Card> poolCards = new ArrayList<Card>();

	public void drawCard() {
		if (poolCards.size() == 0) {
			for (int i = 0; i < players.size(); i++) {
				poolCards.addAll(players.get(i).cards);
			}
			Collections.shuffle(poolCards);
		}
		for (int i = 4; i >= 0; i--) {
			currentCards.add(poolCards.get(i));
			poolCards.remove(i);
		}
	}
	
	public void addPlayer(Servent servent) {
		FriendUnit u = generateFriendUnit(servent);
		u.index = players.size();
		players.add(u);
	}
	
	public void addEnemy(Servent servent) {
		EnemyUnit u = generateEnemyUnit(servent);
		u.index = enemies.size();
		enemies.add(u);
	}

	public static EnemyUnit generateEnemyUnit(Servent servent) {
		EnemyUnit unit = new EnemyUnit();
		unit.sid = servent.getSid();
		unit.level = servent.getLevel();
		unit.np = 0;

		JSONObject data = Data.instance.serventData.get(unit.sid);
		unit.atk = data.getInteger("atk") + servent.getLevel() * data.getInteger("atkRate") + servent.getAtk();
		unit.hp = data.getInteger("health") + servent.getLevel() * data.getInteger("healthRate") + servent.getHealth();
		unit.maxhp = unit.hp;
		unit.type = data.getInteger("type");

		return unit;
	}

	public static FriendUnit generateFriendUnit(Servent servent) {
		FriendUnit unit = new FriendUnit();
		unit.sid = servent.getSid();
		unit.level = servent.getLevel();
		unit.np = 0;

		JSONObject data = Data.instance.serventData.get(unit.sid);
		unit.atk = data.getInteger("atk") + servent.getLevel() * data.getInteger("atkRate") + servent.getAtk();
		unit.hp = data.getInteger("health") + servent.getLevel() * data.getInteger("healthRate") + servent.getHealth();
		unit.maxhp = unit.hp;
		unit.type = data.getInteger("type");

		JSONArray cards = data.getJSONArray("card");
		for (int i = 0; i < cards.size(); i++) {
			Card card = new Card();
			card.type = cards.getInteger(i);
			card.index = i;
			card.unit = unit;
			unit.cards.add(card);
		}

		return unit;
	}
}
