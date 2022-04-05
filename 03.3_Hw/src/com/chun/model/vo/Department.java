package com.chun.model.vo;

public class Department {

	private String departmentNo;
	private String departmentName;
	private String category;
	private char openYN;
	private int capacity;

	public Department() {
		super();
	}

	public Department(String departmentNo, String departmentName, String category, char openYN, int capacity) {
		super();
		this.departmentNo = departmentNo;
		this.departmentName = departmentName;
		this.category = category;
		this.openYN = openYN;
		this.capacity = capacity;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public char getOpenYN() {
		return openYN;
	}

	public void setOpenYN(char openYN) {
		this.openYN = openYN;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Department [departmentNo=" + departmentNo + ", departmentName=" + departmentName + ", category="
				+ category + ", openYN=" + openYN + ", capacity=" + capacity + "]";
	}

}
