package login.mvc.model;

interface LoginSQL {
	static final String SELECT_USER_ONE = "select * from MEMBER where EMAIL=?";
}
