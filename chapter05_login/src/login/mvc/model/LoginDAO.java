package login.mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Member;

class LoginDAO {
	private DataSource ds;

	LoginDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("cannot find Apache DBCP object(jdbc/myoracle) : "+ne);
		}
	}

	String login(Member member) {
		String sql = "select * from MEMBER where EMAIL=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String pwd = rs.getString("PWD");
				if(pwd.equals(member.getPwd())) {
					return "login-success";
				}else {
					return "login-fail-password";
				}
			}else {
				return "login-fail-exist";
			}
		} catch(SQLException se) {
			return "login-fail";
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException se){
			}		
		}
	}

	Member getUserInfo(String email) {
		String sql = "select * from MEMBER where EMAIL=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int seq = rs.getInt("SEQ");
				String name = rs.getString("NAME");
				String pwd = rs.getString("PWD");
				String phone = rs.getString("PHONE");
				Date rdate = rs.getDate("RDATE");
				Date udate = rs.getDate("UDATE");
				return new Member(seq, name, email, pwd, phone, rdate, udate);
			}else {
				return null;
			}
		} catch(SQLException se) {
			return null;
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException se){
			}		
		}	
	}
}
