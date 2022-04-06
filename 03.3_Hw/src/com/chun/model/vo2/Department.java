package com.chun.model.vo2;

public class Department {

	private String dept;
	private String departmentName;
	private Category category;
	private boolean openYN;
	private int capacity;

	public Department() {
		super();
	}

	public Department(String departmentNo, String departmentName, Category category, boolean openYN, int capacity) {
		super();
		this.dept = departmentNo;
		this.departmentName = departmentName;
		this.category = category;
		this.openYN = openYN;
		this.capacity = capacity;
	}

	public String getDepartmentNo() {
		return dept;
	}

	public void setDepartmentNo(String departmentNo) {
		this.dept = departmentNo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean getOpenYN() {
		return openYN;
	}

	public void setOpenYN(boolean openYN) {
		this.openYN = openYN;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
