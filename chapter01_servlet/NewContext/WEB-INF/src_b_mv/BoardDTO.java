package board.mv.model;

import java.sql.Date;

//Data Tansfer Object
public class BoardDTO {
	private int seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	
	//Constructor
	public BoardDTO() {}
	
	public BoardDTO(int seq, String writer, String email, String subject, Date rdate){
		super();
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.rdate = rdate;
	}
	
	public BoardDTO(int seq, String writer, String email, String subject, String content) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
	}
	
	public BoardDTO(int seq, String writer, String email, String subject, String content, Date rdate) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
	}

	//Getter/Setter
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
}
