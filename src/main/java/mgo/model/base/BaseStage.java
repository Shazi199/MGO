package mgo.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStage<M extends BaseStage<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setParent(java.lang.Integer parent) {
		set("parent", parent);
	}
	
	public java.lang.Integer getParent() {
		return getInt("parent");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setLevel(java.lang.Integer level) {
		set("level", level);
	}
	
	public java.lang.Integer getLevel() {
		return getInt("level");
	}

	public void setConfig(java.lang.String config) {
		set("config", config);
	}
	
	public java.lang.String getConfig() {
		return getStr("config");
	}

}
