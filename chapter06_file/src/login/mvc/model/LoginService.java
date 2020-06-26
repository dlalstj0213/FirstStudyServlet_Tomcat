package login.mvc.model;

import mvc.domain.Member;

public class LoginService {
	//SingleTon
	private LoginDAO dao;
	private static final LoginService LOGIN_INSTANCE = new LoginService();
	
	private LoginService() {
		dao = new LoginDAO();
	}
	
	public static LoginService getInstance() {
		return LOGIN_INSTANCE;
	}
	
	public String formService(Member member) {
		return dao.login(member); 
	}
	
	public Member getUserInfoService(String email) {
		return dao.getUserInfo(email);
	}
}
