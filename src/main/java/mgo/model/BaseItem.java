package mgo.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseItem<M extends BaseItem<M>> extends Model<M> implements IBean {

	public void setSid(java.lang.Integer sid) {
		set("sid", sid);
	}
	
	public java.lang.Integer getSid() {
		return getInt("sid");
	}

	public void setUserid(java.lang.Long userid) {
		set("userid", userid);
	}
	
	public java.lang.Long getUserid() {
		return getLong("userid");
	}

	public void setAmount(java.lang.Integer amount) {
		set("amount", amount);
	}
	
	public java.lang.Integer getAmount() {
		return getInt("amount");
	}

}
