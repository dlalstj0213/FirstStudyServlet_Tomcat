package rhie.db;

import java.sql.*;
import java.util.*;

public class ConnectionPoolBean{
	String url, usr, pwd;
	Hashtable<Connection,Boolean> h; //pool��
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
		for(int i=0; i<5; i++) {//5���� ����
			con = DriverManager.getConnection(url, usr, pwd);
			h.put(con, Boolean.FALSE);//��� ��=false //�ؽ����̺� ����
		}
		System.out.println("ConnectionPoolBean created ...");
	}
	public synchronized Connection getConnection()//�Ҹ��������ؼ� 
		throws SQLException {
		Connection con = null;
		Boolean b = null;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){
			con = e.nextElement();
            b = h.get(con);
			if(!b.booleanValue()){//false�� �ɸ��� true�� �ٲ��ְ� �װ��� �����Ѵ�
				h.put(con, Boolean.TRUE);//���ϴ� ��
				return con;//������ ������ �ؿ� ���� ���� ����
			}
		}
		for(int i=0; i<increment; i++){//������ ������ ������ ��ũ�� ����3�� ����
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
            if(con == returnCon){//���ϰ� �ִ� Ŀ�ؼ� > ��� ������ �ٲٱ�
				h.put(con, Boolean.FALSE);
				break;
			}
		}
        keepConSu(5);
	}
	public void keepConSu(int su) throws SQLException{//Ŀ�ؼ� �� 5���� �����϶�
        Connection con = null;
		Boolean b = null;
		int count = 0;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){
			con = e.nextElement();
			b = h.get(con);
			if(!b.booleanValue()){
				count++; //��� ���� ���� 
				if(count >= (su+1)){
					h.remove(con);//Ǯ�忡�� ���� 
					con.close();//Ŀ�ؼ� �ݱ�
				}
			}
		}
	}
	public void closeAll() throws SQLException{
        Connection con = null;
		Enumeration<Connection> e = h.keys();
		while(e.hasMoreElements()){//Ǯ�忡 �ִ� Ŀ�ؼ� ���� ����
            con = e.nextElement();
			h.remove(con);
			con.close();
		}
	}
}
