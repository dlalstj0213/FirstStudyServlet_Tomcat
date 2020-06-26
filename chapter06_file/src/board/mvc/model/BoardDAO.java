package board.mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Board;

class BoardDAO{
	private DataSource ds;
	
	BoardDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("cannot find Apache DBCP object(jdbc/myoracle) : "+ne);
		}
	}
	
	ArrayList<Board> list(){
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = BoardSQL.LIST;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt("SEQ");
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				java.sql.Date rdate = rs.getDate("RDATE");	
				
				list.add(new Board(seq, writer, email, subject, rdate));
			}
			return list;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}	
	}
	
	boolean insert(Board board){
		String sql= BoardSQL.INSERT;
       
		Connection con = null;
		PreparedStatement pstmt = null;
	    try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}		
	}
	
	Board showContent(int seq){
		String sql= BoardSQL.SELECT_SPEC;
		Board resultBoard = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				
				resultBoard = new Board(seq, writer, email, subject, content);
			}
			return resultBoard;
		}catch(SQLException se){
			System.out.println("SQLException : "+se);
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
	
	Board showUpdatePage(int seq){
		String sql = BoardSQL.SELECT_SPEC;
		Board resultBoard = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				
				resultBoard = new Board(seq, writer, email, subject, content);
			}
			return resultBoard;
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
	
	boolean updatePost(Board board){
		String sql = BoardSQL.UPDATE;	
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getEmail());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getSeq());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	
	boolean deletePost(int seq){
		String sql = BoardSQL.DELETE;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();		
			}catch(SQLException se){
			}
		}		
	}	
}