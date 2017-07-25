#sql("findUserByUsername")
	select * from user where username=?
#end

#sql("findUserByOauth")
	select * from user join oauth on user.id=oauth.uid where oauth.authid=? and oauth.type=?
#end