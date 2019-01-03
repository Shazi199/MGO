package mgo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;

import mgo.model.base.BaseTeamMember;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class TeamMember extends BaseTeamMember<TeamMember> {
	public List<TeamMember> findTeamMembersByTeamId(long teamId) {
		return find(getSql("findTeamMembersByTeamId"), teamId);
	}
	
	public void deleteTeamMembersByTeamId(long teamId) {
		Db.update(getSql("deleteTeamMembersByTeamId"), teamId);
	}
}
