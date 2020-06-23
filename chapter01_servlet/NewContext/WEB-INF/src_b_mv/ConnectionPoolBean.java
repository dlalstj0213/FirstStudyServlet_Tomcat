package rhie.db;

import java.sql.*;
import java.util.*;

public class ConnectionPoolBean{
	String url, usr, pwd;
	Hashtable<Connection,Boolean> h; //pool장
	int increment = 3;

	public ConnectionPoolBean() 
		throws ClassNotFoundException, SQLException {
		Connection con = null;
        try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe){
            Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		usr = "servlet";
		pwd = "java";
		h = new Hashtable<Connection,Boolean>();
		for(int i=0; i<5; i++) {//5개를 만듬
			con = DriverManager.getConnection(url, usr, pwd);
			h.put(con, Boolean.FALSE);//노는 놈=false //해쉬테이블에 넣음
		}
		System.out.println("ConnectionPoolBean created ...");
	}
	public synchronized Connection getConnection()//불린값조사해서 
		throws SQLException {
		Connection con = null;
		Boolean b = null;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){
			con = e.nextElement();
            b = h.get(con);
			if(!b.booleanValue()){//false가 걸리면 true로 바꿔주고 그것을 리턴한다
				h.put(con, Boolean.TRUE);//일하는 놈
				return con;//리턴을 만나면 밑에 포문 수행 ㄴㄴ
			}
		}
		for(int i=0; i<increment; i++){//리턴을 만나지 않으면 인크리 ㄱㄱ3개 증가
            h.put(DriverManager.getConnection(url,usr,pwd), 
				    Boolean.FALSE);
		}
        return getConnection();
	}
	public void returnConnection(Connection returnCon)
		throws SQLException {
        Connection con = null;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){
			con = e.nextElement();
            if(con == returnCon){//일하고 있는 커넥션 > 노는 놈으로 바꾸기
				h.put(con, Boolean.FALSE);
				break;
			}
		}
        keepConSu(5);
	}
	public void keepConSu(int su) throws SQLException{//커넥션 수 5개로 유지하라
        Connection con = null;
		Boolean b = null;
		int count = 0;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){
			con = e.nextElement();
			b = h.get(con);
			if(!b.booleanValue()){
				count++; //노는 놈의 갯수 
				if(count >= (su+1)){
					h.remove(con);//풀장에서 제거 
					con.close();//커넥션 닫기
				}
			}
		}
	}
	public void closeAll() throws SQLException{
        Connection con = null;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){//풀장에 있는 커넥션 전부 종료
            con = e.nextElement();
			h.remove(con);
			con.close();
		}
	}
}
