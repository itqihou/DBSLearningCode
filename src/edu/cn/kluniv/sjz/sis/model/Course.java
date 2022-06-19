package edu.cn.kluniv.sjz.sis.model;

public class Course {
	private String Cno;
	private String Cname;
	private String Tno;
	private String Cpno;
	private int Ccredit;

	public Course(String cno, String cname, String tno, String cpno, int ccredit) {
		super();
		Cno = cno;
		Cname = cname;
		Tno = tno;
		Cpno = cpno;
		Ccredit = ccredit;
	}

	public String getCno() {
		return Cno;
	}

	public void setCno(String cno) {
		Cno = cno;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getTno() {
		return Tno;
	}

	public void setTno(String tno) {
		Tno = tno;
	}

	public String getCpno() {
		return Cpno;
	}

	public void setCpno(String cpno) {
		Cpno = cpno;
	}

	public int getCcredit() {
		return Ccredit;
	}

	public void setCcredit(int ccredit) {
		Ccredit = ccredit;
	}

}
