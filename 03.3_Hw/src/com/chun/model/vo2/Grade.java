package com.chun.model.vo2;

public class Grade {

	private String termNo;
	private Student student;
	private Class univClass;
	private double piont;

	public Grade() {
		super();
	}

	public Grade(String termNo, Student student, Class univClass, double piont) {
		super();
		this.termNo = termNo;
		this.student = student;
		this.univClass = univClass;
		this.piont = piont;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Class getUnivClass() {
		return univClass;
	}

	public void setUnivClass(Class univClass) {
		this.univClass = univClass;
	}

	public double getPiont() {
		return piont;
	}

	public void setPiont(double piont) {
		this.piont = piont;
	}

}
