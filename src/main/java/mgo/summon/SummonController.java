package mgo.summon;

import com.jfinal.core.Controller;

import mgo.config.Data;
import mgo.pool.Pool;
import mgo.pool.Pool.Card;

public class SummonController extends Controller {
	public void index() {
		setAttr("pools", Data.instance.poolData);
		renderTemplate("index.html");
	}

	public void drawOne() {
		int poolId = getParaToInt("poolId");

		Pool pool = Data.instance.poolData.get(poolId);
		Card c = pool.drawOne();
		
		renderJson(c);
	}
	
	public void drawTen() {
		int poolId = getParaToInt("poolId");

		Pool pool = Data.instance.poolData.get(poolId);
		Card[] cards = pool.drawTen();
		
		renderJson(cards);
	}
}
