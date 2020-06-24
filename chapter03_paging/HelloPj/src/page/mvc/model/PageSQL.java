package page.mvc.model;

interface PageSQL {
	static final String SELECT_ALL_ROWNUM ="select * from (select ROWNUM rnum, aa.* from (select * from BOARD order by SEQ desc) aa) where rnum>? and rnum<=?";
	static final String SELECT_COUNT_ALL = "select count(*) from BOARD";
}
