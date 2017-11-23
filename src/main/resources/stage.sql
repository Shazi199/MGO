#sql("findStagesByParentId")
	select * from stage where parent=? order by id
#end