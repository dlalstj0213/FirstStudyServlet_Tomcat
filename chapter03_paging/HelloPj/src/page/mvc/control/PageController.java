package page.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import page.mvc.model.PageService;
import page.mvc.vo.ListResult;

@WebServlet("/paging/page.do")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("list")) {
				getListResult(request, response);
			} 
		} else {
			getListResult(request, response);
		}
	}

	private void getListResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(1) cp
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		HttpSession session = request.getSession();
		int currentPage = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				currentPage = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			currentPage = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", currentPage);

		//(2) ps 
		int pageSize = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				pageSize = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);

			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					currentPage = 1;
					session.setAttribute("cp", currentPage);
				}
			}else {
				if(pageSize != psParam) {
					currentPage = 1;
					session.setAttribute("cp", currentPage);
				}
			}
			pageSize = psParam;
		}
		session.setAttribute("ps", pageSize);

		PageService service = PageService.getInstance();
		ListResult result = service.getListResultService(currentPage, pageSize);
		request.setAttribute("result", result);

		String view = "paging_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}


