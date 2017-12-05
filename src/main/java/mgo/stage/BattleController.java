package mgo.stage;

import com.jfinal.core.Controller;

import mgo.battle.Battle;

public class BattleController extends Controller {
	public void getEnemiesInfo() {
		Battle battle = getSessionAttr(StageController.SESSION_BATTLE_KEY);
		renderJson(battle.enemies);
	}

	public void getFriendsInfo() {
		Battle battle = getSessionAttr(StageController.SESSION_BATTLE_KEY);
		renderJson(battle.players);
	}
}
