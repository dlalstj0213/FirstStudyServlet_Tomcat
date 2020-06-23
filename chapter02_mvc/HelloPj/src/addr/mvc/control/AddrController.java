package addr.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import addr.mvc.model.AddressService;
import mvc.domain.Address;


@WebServlet("/addr_mvc/addr.do")
public class AddrController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("del")) {
				del(request, response);
			}else if(m.equals("input")){
				input(request, response);
			}else if(m.equals("insert")) {
				insert(request, response);
			}else {
				list(request, response);
			}
		}else {
			list(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressService service = AddressService.getInstance();
		ArrayList<Address> list = service.listS();

		request.setAttribute("list", list);

		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressService service = AddressService.getInstance();
		String seqStr = request.getParameter("seq");
		int seq = 0;
		if(seqStr != null) {
			seqStr = seqStr.trim();
			seq = Integer.parseInt(seqStr);
			service.delS(seq);
		}
		response.sendRedirect("addr.do");
	}

	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = "input.jsp";
		response.sendRedirect(view);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		Address dto = new Address(-1, name, addr, null);
		
		AddressService service = AddressService.getInstance();
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag); //boolean -> Boolean -> Object
		
		String view = "insert.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
