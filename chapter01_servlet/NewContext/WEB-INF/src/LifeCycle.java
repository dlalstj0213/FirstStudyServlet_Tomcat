package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LifeCycle extends HttpServlet {
	public void init() throws ServletException{ //메모리에 로딩 (첫번째 요청에 의해)
		System.out.println("init()");
	}
	
	public void destroy(){ //메모리에 언로딩 (서버안전파킹 시, 서버정책, 서블릿클래스 갱신)
		try{
			System.out.println("destroy()");
			FileWriter fw = new FileWriter("life.txt");
			PrintWriter pw = new PrintWriter(fw, true);
			pw.println("destroy() 호출됐당");			
		}catch(IOException io){
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{ //요청 시 마다
		System.out.println("service()");

	}
}