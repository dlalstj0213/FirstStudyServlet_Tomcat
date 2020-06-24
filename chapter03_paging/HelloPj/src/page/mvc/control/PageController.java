package page.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import page.mvc.model.PageService;
import page.mvc.vo.ListResult;

@WebServlet("/paging/page.do")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageSize;
	int currentPage;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("list")) {
				getListResult(request, response);
			} else if(m.equals("sizeChange")) {
				getDifferentSizeOfListResult(request, response);
			}
		} else {
			getListResult(request, response);
		}
	}

	private void getListResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp = request.getParameter("cp").trim();
		currentPage = Integer.parseInt(cp);
		
		if(pageSize == 0) {
			String ps = request.getParameter("ps").trim();
			pageSize = Integer.parseInt(ps);
		}

		PageService service = PageService.getInstance();
		ListResult result = service.getListResultService(currentPage, pageSize);
		request.setAttribute("result", result);

		String view = "paging_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void getDifferentSizeOfListResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ps = request.getParameter("ps").trim();
		pageSize = Integer.parseInt(ps);
		System.out.println(ps);

		PageService service = PageService.getInstance();
		ListResult result = service.getListResultService(currentPage, pageSize);
		request.setAttribute("result", result);

		String view = "paging_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);		
	}
}


