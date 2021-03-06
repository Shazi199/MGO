package mgo.model;

import java.util.List;

import com.jfinal.aop.Aop;

import mgo.model.base.BaseTeam;
import mgo.service.TeamService;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Team extends BaseTeam<Team> {

    public List<Servent> getMembers() {
        TeamService teamService = Aop.get(TeamService.class);
        return teamService.getMembers(this.getId());
    }
}
