package file.mvc.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import file.mvc.model.FileSet;

@WebServlet("/file/file.do")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String m = "";   
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "form" : form(request, response); break;
				case "upload" : upload(request, response); break;
				case "list" : list(request, response);break;
				case "del" : del(request, response);break;
				case "download" : download(request, response);break;
				default : list(request, response); break;
			}
		} else {
			list(request, response);
		}
	}
	
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "form.jsp";
		response.sendRedirect(view);
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		File fSaveDir = new File(saveDir);
		if(fSaveDir.exists()) fSaveDir.mkdirs(); //mkdirs() 하위 디렉토리까지 만들 수 있음
		int maxPostSize = 1*1024*1024;
		String encoding ="utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, saveDir, maxPostSize, encoding, policy);
			String writer = mr.getParameter("writer");
			String fname = mr.getFilesystemName("fname");
			String ofname =mr.getOriginalFileName("fname");
			
			System.out.println("업로드 성공 => writer : "+writer+"/ fname : "+fname+"/ ofname : "+ofname);
		} catch(IOException io) {
			System.out.println("업로드 실패 : "+io);
		}
		String view = "file.do";
		response.sendRedirect(view);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		File f = new File(saveDir);
		File[] kids = f.listFiles();
		if(kids.length > 0) request.setAttribute("kids", kids);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		String fname = request.getParameter("fname");
		File f = new File(saveDir, fname);
		if(f.exists())	f.delete();
		
		String view = "file.do";
		response.sendRedirect(view);
	}
	private void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		String fname = request.getParameter("fname");
		File f = new File(saveDir, fname);
		
		//브라우져 설정
		response.setContentType("application/octet-stream"); 
		String Agent=request.getHeader("USER-AGENT");
		if( Agent.indexOf("MSIE") >= 0 ){
			int i = Agent.indexOf( 'M', 2 );

			String IEV = Agent.substring( i + 5, i + 8 );

			if( IEV.equalsIgnoreCase("5.5") ){
				response.setHeader("Content-Disposition", "filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
			}else{
				response.setHeader("Content-Disposition", "attachment;filename="+new String(fname.getBytes("utf-8"),"8859_1"));
			}
		}else{
			response.setHeader("Content-Disposition", "attachment;filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
		}
		//다운로드 구현
		if(f.exists() && f.isFile()) {
			FileInputStream fis = null; // Data Source(서버에 저장된 File 과 연결된 입력스트림 객체)
			BufferedInputStream bis = null;
			
			OutputStream os = null; // Data Destination(클라이언트 브라우져와 연결된 출력스트림 객체)
			BufferedOutputStream bos = null;
			try {
				fis = new FileInputStream(f);
				bis = new BufferedInputStream(fis, 1024);
				
				os = response.getOutputStream();
				bos = new BufferedOutputStream(os, 1024);
				
				int count = 0;
				byte[] bs = new byte[512];
				while((count = bis.read(bs)) != -1) {
					bos.write(bs, 0, count);
				}
				bos.flush();
			}catch(IOException io) {
			}finally {
				try {
					if(bos != null) bos.close();
					if(bis != null) bis.close();
					if(fis != null) fis.close();
					if(os != null) os.close();
				}catch(IOException ie) {
				}
			}
		}
	}
}
