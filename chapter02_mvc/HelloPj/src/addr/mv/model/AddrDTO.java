package addr.mv.model;

import java.sql.Date;

//Data Transfer Object
public class AddrDTO {
	private int seq;
	private String name;
	private String addr;
	private Date rdate;
	
	//Constructor
	public AddrDTO() {
	}
	public AddrDTO(int seq, String name, String addr, Date rdate) {
		this.seq=seq;
		this.name=name;
		this.addr =addr;
		this.rdate= rdate;
	}
	
	//getter/setter
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
}
