package com.chun.model.vo2;

public class Class {

	private String classNo;
	private Department deptNo;
	private Class preattendingClass;
	private String className;
	private ClassType classType;

	public Class() {
		super();
	}

	public Class(String classNo, Department deptNo, Class preattendingClass, String className, ClassType classType) {
		super();
		this.classNo = classNo;
		this.deptNo = deptNo;
		this.preattendingClass = preattendingClass;
		this.className = className;
		this.classType = classType;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public Department getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Department deptNo) {
		this.deptNo = deptNo;
	}

	public Class getPreattendingClass() {
		return preattendingClass;
	}

	public void setPreattendingClassNo(Class preattendingClass) {
		this.preattendingClass = preattendingClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

}
