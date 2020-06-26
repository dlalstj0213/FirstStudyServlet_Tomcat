package board.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.mvc.model.BoardService;
import mvc.domain.Board;

@WebServlet("/board_mvc/board.do")
public class BoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("write")) {
				write(request, response);
			}else if(m.equals("insert")){
				insert(request, response);
			}else if(m.equals("content")) {
				showContent(request, response);
			}else if(m.equals("updatePage")) {
				showUpdatePage(request, response);
			}else if(m.equals("delete")) {
				deletePost(request, response);
			}else if(m.equals("update")) {
				updatePost(request, response);
			}else {
				list(request, response);
			}
		}else {
			list(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listService();

		request.setAttribute("list", list);

		String view = "b_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "write.jsp";
		response.sendRedirect(view);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		Board board = new Board(-1, writer, email, subject, content, null);
		BoardService service = BoardService.getInstance();
		boolean flag = service.insertService(board);
		request.setAttribute("flag", flag);
		
		String view = "insert.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void showContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		String seqStr = request.getParameter("seq");
	
		if(seqStr != null) {
			int seq = Integer.parseInt(seqStr.trim());
			Board board = service.showContentService(seq);
			request.setAttribute("board", board);
		}
		
		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void showUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		String seqStr = request.getParameter("seq");
		
		if(seqStr != null) {
			int seq = Integer.parseInt(seqStr.trim());
			Board board = service.showUpdatePageService(seq);
			request.setAttribute("board", board);
		}
		
		String view = "update_page.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = 0;
		String seqStr = request.getParameter("seq");
		if(seqStr != null) seq = Integer.parseInt(seqStr.trim());
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		Board board = new Board(seq, writer, email, subject, content, null);
		BoardService service = BoardService.getInstance();
		boolean flag = service.updatePostService(board);
		request.setAttribute("flag", flag);
		
		String view = "update.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		String seqStr = request.getParameter("seq");
		
		if(seqStr != null) {
			int seq = Integer.parseInt(seqStr.trim());
			boolean flag = service.deleteService(seq);
			request.setAttribute("flag", flag);
		}
		
		String view = "delete.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	
}