package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LifeCycle extends HttpServlet {
	public void init() throws ServletException{ //�޸𸮿� �ε� (ù��° ��û�� ����)
		System.out.println("init()");
	}
	
	public void destroy(){ //�޸𸮿� ��ε� (����������ŷ ��, ������å, ����Ŭ���� ����)
		try{
			System.out.println("destroy()");
			FileWriter fw = new FileWriter("life.txt");
			PrintWriter pw = new PrintWriter(fw, true);
			pw.println("destroy() ȣ��ƴ�");			
		}catch(IOException io){
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{ //��û �� ����
		System.out.println("service()");

	}
}