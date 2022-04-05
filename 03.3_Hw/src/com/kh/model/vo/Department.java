package com.kh.model.vo;

public class Department extends Employee {

	private String deptId;
	private String deptTitle;
	private String lacationId;

	public Department() {
		super();
	}

	public Department(String deptId, String deptTitle, String lacationId) {
		super();
		this.deptId = deptId;
		this.deptTitle = deptTitle;
		this.lacationId = lacationId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public String getLacationId() {
		return lacationId;
	}

	public void setLacationId(String lacationId) {
		this.lacationId = lacationId;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + ", lacationId=" + lacationId + "]";
	}

}
