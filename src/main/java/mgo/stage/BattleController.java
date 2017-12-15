package mgo.stage;

import java.util.List;

import com.jfinal.core.Controller;

import mgo.battle.Battle;
import mgo.battle.Result;
import mgo.util.Message;

public class BattleController extends Controller {
	public void getEnemiesInfo() {
		Battle battle = getSessionAttr(StageController.SESSION_BATTLE_KEY);
		renderJson(battle.enemies);
	}

	public void getFriendsInfo() {
		Battle battle = getSessionAttr(StageController.SESSION_BATTLE_KEY);
		renderJson(battle.players);
	}
	
	public void getCardsInfo() {
		Battle battle = getSessionAttr(StageController.SESSION_BATTLE_KEY);
		renderJson(battle.currentCards);
	}
	
	public void run() {
		Battle battle = getSessionAttr(StageController.SESSION_BATTLE_KEY);
		String selectCards = getPara("selectCards");
		String[] spilited = selectCards.split(",");
		int[] cards = new int[3];
		for (int i = 0; i < spilited.length; i++) {
			cards[i] = Integer.valueOf(spilited[i]);
		}
		List<Result> results = battle.run(cards);
		renderJson(results);
	}

	public void endBattle() {
		removeSessionAttr(StageController.SESSION_BATTLE_KEY);
		renderJson(Message.ok());
	}
}
