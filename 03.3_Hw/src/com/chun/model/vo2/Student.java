package com.chun.model.vo2;

import java.sql.Date;

public class Student {

	private String studentNo;
	private Department dept;
	private String studentName;
	private String ssn;
	private String address;
	private Date entranceDate;
	private boolean absenceYN;
	private Professor coach;

	public Student() {
		super();
	}

	public Student(String studentNo, Department dept, String studentName, String ssn, String address, Date entranceDate,
			boolean absenceYN, Professor coach) {
		super();
		this.studentNo = studentNo;
		this.dept = dept;
		this.studentName = studentName;
		this.ssn = ssn;
		this.address = address;
		this.entranceDate = entranceDate;
		this.absenceYN = absenceYN;
		this.coach = coach;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public boolean getAbsenceYN() {
		return absenceYN;
	}

	public void setAbsenceYN(boolean absenceYN) {
		this.absenceYN = absenceYN;
	}

	public Professor getCoach() {
		return coach;
	}

	public void setCoach(Professor coach) {
		this.coach = coach;
	}

}
