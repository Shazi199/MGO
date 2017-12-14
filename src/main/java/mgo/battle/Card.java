package mgo.battle;

public class Card {
	public Unit unit;
	public int type;
	public int index;
	
	public int getUnitIndex() {
		return unit.index;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
