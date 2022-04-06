package com.chun.model.vo;

import java.sql.Date;

public class Student extends Department {

	private String studentNo;
	private String departmentNo;
	private String studentName;
	private String ssn;
	private String address;
	private Date entranceDate;
	private char absenceYN;
	private String coachProfessorNo;

	public Student() {
		super();
	}

	public Student(String studentNo, String departmentNo, String studentName, String ssn, String address,
			Date entranceDate, char absenceYN, String coachProfessorNo) {
		super();
		this.studentNo = studentNo;
		this.departmentNo = departmentNo;
		this.studentName = studentName;
		this.ssn = ssn;
		this.address = address;
		this.entranceDate = entranceDate;
		this.absenceYN = absenceYN;
		this.coachProfessorNo = coachProfessorNo;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
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

	public char getAbsenceYN() {
		return absenceYN;
	}

	public void setAbsenceYN(char absenceYN) {
		this.absenceYN = absenceYN;
	}

	public String getCoachProfessorNo() {
		return coachProfessorNo;
	}

	public void setCoachProfessorNo(String coachProfessorNo) {
		this.coachProfessorNo = coachProfessorNo;
	}

	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", departmentNo=" + departmentNo + ", studentName=" + studentName
				+ ", ssn=" + ssn + ", address=" + address + ", entranceDate=" + entranceDate + ", absenceYN="
				+ absenceYN + ", coachProfessorNo=" + coachProfessorNo + "]";
	}

}