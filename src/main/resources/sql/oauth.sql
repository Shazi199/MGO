#sql("findOauthByType")
	select * from oauth where uid=? and type=?
#end