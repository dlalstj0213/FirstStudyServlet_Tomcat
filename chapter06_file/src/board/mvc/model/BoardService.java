package board.mvc.model;

import java.util.ArrayList;

import mvc.domain.Board;

public class BoardService{
	//SingleTon Object Model
	private BoardDAO dao;
	private static final BoardService INSTANCE = new BoardService();
	
	private BoardService(){
		dao = new BoardDAO();
	}
	
	public static BoardService getInstance(){
		return INSTANCE;
	}
	
	public ArrayList<Board> listService(){
		return dao.list();
	}
	
	public boolean insertService(Board board) {
		return dao.insert(board);
	}
	
	public Board showContentService(int seq) {
		return dao.showContent(seq);
	}
	
	public Board showUpdatePageService(int seq) {
		return dao.showUpdatePage(seq);
	}
	
	public boolean updatePostService(Board board) {
		return dao.updatePost(board);
	}
	
	public boolean deleteService(int seq) {
		return dao.deletePost(seq);
	}
}