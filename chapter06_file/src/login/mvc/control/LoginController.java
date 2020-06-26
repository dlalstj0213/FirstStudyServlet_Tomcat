package login.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.mvc.model.LoginService;
import mvc.domain.Member;

@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String m = "";

	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("form")) {
				form(request, response);
			}else if(m.equals("in")) {
				login(request, response);
			}else if(m.equals("out")) {
				logout(request, response);
			}
		}else {
			response.sendRedirect("../index.do");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String view = "login.jsp";
		response.sendRedirect(view);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		LoginService service = LoginService.getInstance();
		String result = service.formService(new Member(-1, null, email, pwd, null, null, null));

		if(result.equals("login-success")) {
			Member user = service.getUserInfoService(email);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			request.setAttribute("result", result);
		} else {
			request.setAttribute("result", result);
		}
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		String view = "../index.do";
		response.sendRedirect(view);
	}
}
