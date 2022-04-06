package com.chun.model.vo;

public class ClassProfessor extends Class {

	private String classNo;
	private String professorNo;

	public ClassProfessor() {
		super();
	}

	public ClassProfessor(String classNo, String professorNo) {
		super();
		this.classNo = classNo;
		this.professorNo = professorNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getProfessorNo() {
		return professorNo;
	}

	public void setProfessorNo(String professorNo) {
		this.professorNo = professorNo;
	}

	@Override
	public String toString() {
		return "ClassProfessor [classNo=" + classNo + ", professorNo=" + professorNo + "]";
	}

}