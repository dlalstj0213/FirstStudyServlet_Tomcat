package board.mv.model;

import rhie.db.ConnectionPoolBean;
import java.util.ArrayList;
import java.sql.*;

//Data Access Object
public class BoardDAO{
	private ConnectionPoolBean pool;
	
	public void setPool(ConnectionPoolBean pool){
		this.pool = pool;
	}
	
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select * from BOARD order by SEQ desc";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = pool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt("SEQ");
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				java.sql.Date rdate = rs.getDate("RDATE");	
				
				list.add(new BoardDTO(seq, writer, email, subject, rdate));
			}
			return list;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}	

	}
	
	public boolean insert(BoardDTO dto){
		String sql="insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
       
		Connection con = null;
		PreparedStatement pstmt = null;
	    try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
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
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}		
	}
	
	public ArrayList<BoardDTO> showContent(BoardDTO dto){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql= "select * from BOARD where SEQ = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getSeq());
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				
				list.add(new BoardDTO(dto.getSeq(), writer, email, subject, content));
			}
			return list;
		}catch(SQLException se){
			System.out.println("SQLException : "+se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();	
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);				
			}catch(SQLException se){
			}
		}
	}
	
	public ArrayList<BoardDTO> showUpdatePage(BoardDTO dto){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select * from BOARD where SEQ=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getSeq());
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				
				list.add(new BoardDTO(dto.getSeq(), writer, email, subject, content));
			}
			return list;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
	}
	
	public boolean updatePost(BoardDTO dto){
		String sql = "update BOARD set EMAIL = ?, SUBJECT = ?, CONTENT = ? where SEQ=?";	
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getSeq());
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
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
	}
	
	public boolean deletePost(BoardDTO dto){
		String sql = "delete from BOARD where SEQ=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getSeq());
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
				if(con != null) pool.returnConnection(con);		
			}catch(SQLException se){
			}
		}		
	}
}