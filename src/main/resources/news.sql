#sql("findAllNewsForPagination_select")
	select id,title,create_time
#end
#sql("findAllNewsForPagination_sqlExceptSelect")
	from news order by id desc
#end