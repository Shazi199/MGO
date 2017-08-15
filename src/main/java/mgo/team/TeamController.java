package mgo.team;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;

import mgo.model.Servent;
import mgo.model.Team;
import mgo.model.TeamMember;
import mgo.model.User;
import mgo.util.Message;

public class TeamController extends Controller {
	public void index() {
		renderTemplate("index.html");
	}
	
	public void loadTeam() {
		User u = getSessionAttr("currentUser");
		
		List<Team> datas = new Team().findTeamsByUserid(u.getId());
		setAttr("datas", datas);
		
		renderJson(datas);
	}

	@Before(Tx.class)
	public void deleteTeam() {
		User u = getSessionAttr("currentUser");

		long teamId = getParaToLong("teamId", -1l);
		
		Team team = new Team().findById(teamId);
		if (team == null || !u.getId().equals(team.getUserid())) {
			renderJson(Message.fail("无效队伍"));
			return;
		}
		
		team.delete();
		renderJson(Message.ok());
	}
	
	@Before(Tx.class)
	public void saveOrUpdateTeam() {
		User u = getSessionAttr("currentUser");
		
		long teamId = getParaToLong("teamId", -1l);
		String teamName = getPara("teamName");
		Long[] teamServent = getParaValuesToLong("teamServent[]");
		
		if (StrKit.isBlank(teamName)) {
			renderJson(Message.fail("队伍名不能为空"));
			return;
		}
		
		if (teamServent == null || teamServent.length == 0) {
			renderJson(Message.fail("队伍成员不能为空"));
			return;
		}
		
		List<Long> serventIds = new ArrayList<Long>();
		for (int i = 0; i < teamServent.length; i++) {
			Long serventId = teamServent[i];
			if (serventId == null || serventIds.contains(serventId)) {
				renderJson(Message.fail("不能使用同一个角色"));
				return;
			}
			Servent s = new Servent().findById(serventId);
			if (s == null || !u.getId().equals(s.getUserid()) ) {
				renderJson(Message.fail("无效角色"));
				return;
			}
			
			serventIds.add(serventId);
		}
		
		if (teamId == -1) {
			Team team = new Team();
			team.setTeamname(teamName);
			team.setUserid(u.getId());
			team.save();

			for (int i = 0; i < serventIds.size(); i++) {
				TeamMember member = new TeamMember();
				member.setTeamid(team.getId());
				member.setServentid(serventIds.get(i));
				member.setOrder(i);
				member.save();
			}
		} else {
			Team team = new Team().findById(teamId);
			if (team == null || !u.getId().equals(team.getUserid())) {
				renderJson(Message.fail("无效队伍"));
				return;
			}
			
			team.setTeamname(teamName);
			team.update();
			
			List<TeamMember> oldMembers = new TeamMember().findTeamMembersByTeamId(teamId);
			for (int i = 0; i < oldMembers.size(); i++) {
				oldMembers.get(i).delete();
			}

			for (int i = 0; i < serventIds.size(); i++) {
				TeamMember member = new TeamMember();
				member.setTeamid(team.getId());
				member.setServentid(serventIds.get(i));
				member.setOrder(i);
				member.save();
			}
		}
		

		renderJson(Message.ok());
	}
}
