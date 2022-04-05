package com.chun.model.vo;

public class Grade extends Student {

	private String termNo;
	private String studentNo;
	private String classNo;
	private double piont;

	public Grade() {
		super();
	}

	public Grade(String termNo, String studentNo, String classNo, double piont) {
		super();
		this.termNo = termNo;
		this.studentNo = studentNo;
		this.classNo = classNo;
		this.piont = piont;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public double getPiont() {
		return piont;
	}

	public void setPiont(double piont) {
		this.piont = piont;
	}

	@Override
	public String toString() {
		return "Grade [termNo=" + termNo + ", studentNo=" + studentNo + ", classNo=" + classNo + ", piont=" + piont
				+ "]";
	}

}
