#sql("findTeamsByUserid")
	select * from team where userid=? order by id
#end

#sql("getTeamMembers")
	select servent.* from servent join team_member on servent.id=team_member.serventid where team_member.teamid=? order by team_member.order
#end