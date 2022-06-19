package edu.cn.kluniv.sjz.sis.model;

public class SC {
	private String Sno;
	private String Sname;
	private String Sdept;
	private String Cno;
	private String Cname;
	private String Tname;
	private int grade;
	private String term;

	public SC(String sno, String sname, String sdept, String cno, String cname, String tname, int grade, String term) {
		super();
		Sno = sno;
		Sname = sname;
		Sdept = sdept;
		Cno = cno;
		Cname = cname;
		Tname = tname;
		this.grade = grade;
		this.term = term;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSdept() {
		return Sdept;
	}

	public void setSdept(String sdept) {
		Sdept = sdept;
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

	public String getTname() {
		return Tname;
	}

	public void setTname(String tname) {
		Tname = tname;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

}
