package page.mvc.model;

import page.mvc.vo.ListResult;

public class PageService {
	private PageDAO dao;
	private static final PageService PAGE_INSTANCE = new PageService();
	
	private PageService() {
		dao = new PageDAO();
	}
	
	public static PageService getInstance() {
		return PAGE_INSTANCE;
	}
	
	public ListResult getListResultService(int currentPage, int pageSize) {
		return dao.getListResult(currentPage, pageSize);
	}
}
