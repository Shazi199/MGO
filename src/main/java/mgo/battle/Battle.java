package mgo.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import mgo.config.Data;
import mgo.model.Servent;

public class Battle {
	public List<Unit> players = new ArrayList<Unit>();
	public List<Unit> enemies = new ArrayList<Unit>();
	
	public List<Card> currentCards = new ArrayList<Card>();
	public List<Card> usedCards = new ArrayList<Card>();
	public List<Card> leftCards = new ArrayList<Card>();
	
	public void initCard() {
		for (int i = 0; i < 3 && i < players.size(); i++) {
			leftCards.addAll(players.get(i).cards);
		}
		shuffleCards();
		drawCard();
	}

	private void drawCard() {
		if (leftCards.size() == 0) {
			leftCards.addAll(usedCards);
			usedCards.clear();
		}
		for (int i = 4; i >= 0; i--) {
			currentCards.add(leftCards.get(i));
			leftCards.remove(i);
		}
	}

	private void shuffleCards() {
		Collections.shuffle(leftCards);
	}
	
	public static Unit generateUnit(Servent servent) {
		Unit unit = new Unit();
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
