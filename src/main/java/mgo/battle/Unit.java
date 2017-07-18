package mgo.battle;

import java.util.ArrayList;
import java.util.List;

public class Unit {
	public int hp;
	public int maxhp;
	public int atk;
	public int np;
	public int type;
	public int sid;
	public int level;
	
	public List<Card> cards = new ArrayList<Card>();
}
