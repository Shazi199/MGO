package mgo.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;

import mgo.model.TeamMember;

public class TeamMemberService {
    public final TeamMember dao = new TeamMember().dao();

    public List<TeamMember> findTeamMembersByTeamId(long teamId) {
        return dao.find(dao.getSql("findTeamMembersByTeamId"), teamId);
    }

    public void deleteTeamMembersByTeamId(long teamId) {
        Db.update(dao.getSql("deleteTeamMembersByTeamId"), teamId);
    }
}
