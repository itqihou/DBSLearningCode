package edu.cn.kluniv.sjz.sis.model;

public class Administrator {
	private String Ano;
	private String Aname;
	private String Asex;
	private int Aage;
	private String Adept;

	public Administrator(String ano, String aname, String asex, int aage, String adept) {
		super();
		Ano = ano;
		Aname = aname;
		Asex = asex;
		Aage = aage;
		Adept = adept;
	}

	public String getAno() {
		return Ano;
	}

	public void setAno(String ano) {
		Ano = ano;
	}

	public String getAname() {
		return Aname;
	}

	public void setAname(String aname) {
		Aname = aname;
	}

	public String getAsex() {
		return Asex;
	}

	public void setAsex(String asex) {
		Asex = asex;
	}

	public int getAage() {
		return Aage;
	}

	public void setAage(int aage) {
		Aage = aage;
	}

	public String getAdept() {
		return Adept;
	}

	public void setAdept(String adept) {
		Adept = adept;
	}

}
