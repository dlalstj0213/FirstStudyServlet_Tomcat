package page.mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Board;
import page.mvc.vo.ListResult;

class PageDAO {
	private DataSource ds;
	
	PageDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("cannot find Apache DBCP object(jdbc/myoracle) : "+ne);
		}
	}
	
	public ListResult getListResult(int currentPage, int pageSize) {
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			long totalCount = calTotalCount(con);
			pstmt = con.prepareStatement(PageSQL.SELECT_ALL_ROWNUM);
			pstmt.setInt(1, (currentPage-1)*pageSize);
			pstmt.setInt(2, currentPage*pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int seq = rs.getInt("SEQ");
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				Date rdate = rs.getDate("RDATE");
				list.add(new Board(seq, writer, email, subject, null, rdate));
			}
			return new ListResult(currentPage, totalCount, pageSize, list);
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}			
	}
	
	private long calTotalCount(Connection con) {
		long totalCount = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(PageSQL.SELECT_COUNT_ALL);
			if(rs.next()) {
				totalCount = rs.getInt(1);
				return totalCount;
			} else {
				return -1L;
			}
		} catch(SQLException se) {
			return -1L;
		} finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(SQLException se){
			}			
		}
	}
}
