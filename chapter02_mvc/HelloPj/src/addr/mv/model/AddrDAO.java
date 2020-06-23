package addr.mv.model;

import java.util.ArrayList;
import rhie.db.ConnectionPoolBean;
import java.sql.*;

//Data Access Object
public class AddrDAO {
	private ConnectionPoolBean pool;

	public void setPool(ConnectionPoolBean pool) {
		this.pool = pool;
	}

	public ArrayList<AddrDTO> list(){
		String sql = "select * from ADDRESS order by SEQ desc";	
		ArrayList<AddrDTO> list = new ArrayList<AddrDTO>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = pool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				Date rdate = rs.getDate(4);

				list.add(new AddrDTO(seq, name, addr, rdate));
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

	public void del(int seq) {
		String sql = "delete from ADDRESS where SEQ=?";
		Connection con = null;
		PreparedStatement pstmt = null;		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
	}
	
	public boolean insert(AddrDTO dto) {
		String sql = "insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException se) {
			return false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					pool.returnConnection(con);
			} catch (SQLException se) {
			}
		}		
	}
}

