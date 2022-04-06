package com.chun.model.vo2;

public class Professor {

	private String professorNo;
	private String professorName;
	private String professorSsn;
	private String professorAddress;
	private Department dept;

	public Professor() {
		super();
	}

	public Professor(String professorNo, String professorName, String professorSsn, String professorAddress,
			Department dept) {
		super();
		this.professorNo = professorNo;
		this.professorName = professorName;
		this.professorSsn = professorSsn;
		this.professorAddress = professorAddress;
		this.dept = dept;
	}

	public String getProfessorNo() {
		return professorNo;
	}

	public void setProfessorNo(String professorNo) {
		this.professorNo = professorNo;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getProfessorSsn() {
		return professorSsn;
	}

	public void setProfessorSsn(String professorSsn) {
		this.professorSsn = professorSsn;
	}

	public String getProfessorAddress() {
		return professorAddress;
	}

	public void setProfessorAddress(String professorAddress) {
		this.professorAddress = professorAddress;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

}
