package com.chun.model.vo2;

public class ClassProfessor {

	private Class univClass;
	private Professor prof;

	public ClassProfessor() {
		super();
	}

	public ClassProfessor(Class univClass, Professor prof) {
		super();
		this.univClass = univClass;
		this.prof = prof;
	}

	public Class getUnivClass() {
		return univClass;
	}

	public void setClassNo(Class univClass) {
		this.univClass = univClass;
	}

	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

}
