package mgo.summon;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.JMap;
import com.jfinal.plugin.activerecord.tx.Tx;

import mgo.config.Data;
import mgo.model.Craft;
import mgo.model.Servent;
import mgo.model.User;
import mgo.pool.Pool;
import mgo.pool.Pool.Card;

public class SummonController extends Controller {
	public void index() {
		int poolId = getParaToInt("poolId", 1);
		setAttr("pool", Data.instance.poolData.get(poolId));
		renderTemplate("index.html");
	}

	@Before(Tx.class)
	public void drawOne() {
		User u = getSessionAttr("currentUser");
		if (u.getStone() < 3) {
			renderJson(JMap.fail("msg", "☯不足3个！"));
			return;
		}
		
		int poolId = getParaToInt("poolId");

		Pool pool = Data.instance.poolData.get(poolId);
		Card c = pool.drawOne();

		saveCard(c, u);
		
		u.setStone(u.getStone() - 3);
		u.update();

		renderJson(c);
	}

	@Before(Tx.class)
	public void drawTen() {
		User u = getSessionAttr("currentUser");
		if (u.getStone() < 30) {
			renderJson(JMap.fail("msg", "☯不足30个！"));
			return;
		}
		int poolId = getParaToInt("poolId");

		Pool pool = Data.instance.poolData.get(poolId);
		Card[] cards = pool.drawTen();

		for (Card c : cards) {
			saveCard(c, u);
		}

		u.setStone(u.getStone() - 30);
		u.update();

		renderJson(cards);
	}

	private void saveCard(Card card, User u) {
		switch (card.getType()) {
		case Pool.CRAFT:
			Craft c = new Craft();
			c.setUserid(u.getId());
			c.setSid(card.getId());
			c.setLevel(1);
			c.setPhase(0);
			c.setExp(0);
			c.save();
			break;
		case Pool.SERVENT:
			Servent s = new Servent();
			s.setUserid(u.getId());
			s.setSid(card.getId());
			s.setLevel(1);
			s.setPhase(0);
			s.setAtk(0);
			s.setHealth(0);
			s.setNoble(1);
			s.setExp(0);
			s.setSkill1(0);
			s.setSkill2(0);
			s.setSkill3(0);
			s.setSkill1level(-1);
			s.setSkill2level(-1);
			s.setSkill3level(-1);
			s.save();
			break;
		}
	}
}
