#sql("findTeamMembersByTeamId")
	select * from team_member where teamid=? order by `order`
#end
#sql("deleteTeamMembersByTeamId")
	delete from team_member where teamid=?
#end