#sql("findAllNewsForPagination")
	select id,title,create_time from news order by id desc
#end
#sql("getLatestNewsCount")
	select count(*) from news where create_time>=DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 7 DAY)
#end