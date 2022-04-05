package com.chun.model.vo;

public class Professor extends ClassProfessor {

	private String professorNo;
	private String professorName;
	private String professorSsn;
	private String professorAddress;
	private String departmentNo;

	public Professor() {
		super();
	}

	public Professor(String professorNo, String professorName, String professorSsn, String professorAddress,
			String departmentNo) {
		super();
		this.professorNo = professorNo;
		this.professorName = professorName;
		this.professorSsn = professorSsn;
		this.professorAddress = professorAddress;
		this.departmentNo = departmentNo;
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

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	@Override
	public String toString() {
		return "Professor [professorNo=" + professorNo + ", professorName=" + professorName + ", professorSsn="
				+ professorSsn + ", professorAddress=" + professorAddress + ", departmentNo=" + departmentNo + "]";
	}

}
