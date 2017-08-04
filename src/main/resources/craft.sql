#sql("findCraftsByUserid")
	select * from craft where userid=? order by created desc
#end