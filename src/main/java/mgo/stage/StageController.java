package mgo.stage;

import java.util.List;

import com.jfinal.core.Controller;

import mgo.battle.Battle;
import mgo.model.Servent;
import mgo.model.Stage;
import mgo.model.Team;
import mgo.model.TeamMember;
import mgo.model.User;
import mgo.util.Message;

public class StageController extends Controller {
	private static final String SESSION_BATTLE_KEY = "session_battle_key";

	public void index() {
		if (getSessionAttr(SESSION_BATTLE_KEY) != null) {
			battleIndex();
			return;
		}
		
		User u = getSessionAttr("currentUser");

		List<Team> teams = new Team().findTeamsByUserid(u.getId());
		setAttr("teams", teams);
		List<Stage> stages = new Stage().findStagesByParentId(0);
		setAttr("stages", stages);

		renderTemplate("index.html");
	}

	public void startBattle() {
		if (getSessionAttr(SESSION_BATTLE_KEY) != null) {
			renderJson(Message.ok());
		}

		long teamId = getParaToLong("teamId", new Long(-1));
		int stageId = getParaToInt("stageId", new Integer(-1));

		Team team = new Team().findById(teamId);
		if (team == null) {
			renderJson(Message.fail("所选队伍不存在"));
			return;
		}
		Stage stage = new Stage().findById(stageId);
		if (stage == null) {
			renderJson(Message.fail("所选关卡不存在"));
			return;
		}

		Battle battle = new Battle();
		List<TeamMember> members = new TeamMember().findTeamMembersByTeamId(team.getId());
		for (TeamMember member : members) {
			Servent s = new Servent().findById(member.getServentid());
			battle.addPlayer(s);
			battle.addEnemy(s);
		}

		setSessionAttr(SESSION_BATTLE_KEY, battle);
		renderJson(Message.ok());
	}
	
	private void battleIndex() {
		renderTemplate("battleIndex.html");
	}
}
