package mgo.model;

import java.util.List;

import mgo.model.base.BaseTeam;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Team extends BaseTeam<Team> {
	public List<Team> findTeamsByUserid(long userId) {
		List<Team> result = find(getSql("findTeamsByUserid"), userId);
		for (Team t : result) {
			List<Servent> members = getMembers(t.getId());
			t.put("members", members);
		}
		return result;
	}

	public List<Servent> getMembers() {
		return getMembers(this.getId());
	}

	private List<Servent> getMembers(long teamId) {
		return new Servent().find(getSql("getTeamMembers"), teamId);
	}
}
