package mgo.service;

import java.util.List;

import com.jfinal.aop.Aop;

import mgo.model.Servent;
import mgo.model.Team;

public class TeamService {
    public final Team dao = new Team().dao();
    
    private ServentService serventService = Aop.get(ServentService.class);

    public List<Team> findTeamsByUserid(long userId) {
        List<Team> result = dao.find(dao.getSql("findTeamsByUserid"), userId);
        for (Team t : result) {
            List<Servent> members = getMembers(t.getId());
            t.put("members", members);
        }
        return result;
    }

    public List<Servent> getMembers(long teamId) {
        return serventService.dao.find(dao.getSql("getTeamMembers"), teamId);
    }
}
