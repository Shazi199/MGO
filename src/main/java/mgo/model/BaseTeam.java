package mgo.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTeam<M extends BaseTeam<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setUserid(java.lang.Long userid) {
		set("userid", userid);
	}

	public java.lang.Long getUserid() {
		return get("userid");
	}

	public void setTeamname(java.lang.String teamname) {
		set("teamname", teamname);
	}

	public java.lang.String getTeamname() {
		return get("teamname");
	}

}
