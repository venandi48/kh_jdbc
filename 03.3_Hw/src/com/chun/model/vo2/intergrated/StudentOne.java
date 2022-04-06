package com.chun.model.vo2.intergrated;

import java.sql.Date;

public class StudentOne {

	private String studentNo;

	private String departmentNo;
	private String departmentName;

	private String studentName;
	private String studentSsn;
	private String studentAddress;
	private Date enteranceDate;
	private boolean absenceYN;

	private String coachProfessorNo;
	private String coachProfessorName;

	public StudentOne() {
		super();
	}

	public StudentOne(String studentNo, String departmentNo, String departmentName, String studentName,
			String studentSsn, String studentAddress, Date enteranceDate, boolean absenceYN, String coachProfessorNo,
			String coachProfessorName) {
		super();
		this.studentNo = studentNo;
		this.departmentNo = departmentNo;
		this.departmentName = departmentName;
		this.studentName = studentName;
		this.studentSsn = studentSsn;
		this.studentAddress = studentAddress;
		this.enteranceDate = enteranceDate;
		this.absenceYN = absenceYN;
		this.coachProfessorNo = coachProfessorNo;
		this.coachProfessorName = coachProfessorName;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSsn() {
		return studentSsn;
	}

	public void setStudentSsn(String studentSsn) {
		this.studentSsn = studentSsn;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Date getEnteranceDate() {
		return enteranceDate;
	}

	public void setEnteranceDate(Date enteranceDate) {
		this.enteranceDate = enteranceDate;
	}

	public boolean isAbsenceYN() {
		return absenceYN;
	}

	public void setAbsenceYN(boolean absenceYN) {
		this.absenceYN = absenceYN;
	}

	public String getCoachProfessorNo() {
		return coachProfessorNo;
	}

	public void setCoachProfessorNo(String coachProfessorNo) {
		this.coachProfessorNo = coachProfessorNo;
	}

	public String getCoachProfessorName() {
		return coachProfessorName;
	}

	public void setCoachProfessorName(String coachProfessorName) {
		this.coachProfessorName = coachProfessorName;
	}

}
