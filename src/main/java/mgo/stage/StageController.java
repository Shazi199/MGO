package mgo.stage;

import java.util.List;

import com.jfinal.core.Controller;

import mgo.model.Stage;
import mgo.model.Team;
import mgo.model.User;

public class StageController extends Controller {
	public void index() {
		User u = getSessionAttr("currentUser");
		
		List<Team> teams = new Team().findTeamsByUserid(u.getId());
		setAttr("teams", teams);
		List<Stage> stages = new Stage().findStagesByParentId(0);
		setAttr("stages", stages);
		
		renderTemplate("index.html");
	}
}
