package com.kh.model.vo;

public class Nation extends Location {

	private String nationalCode;
	private String nationalName;

	public Nation() {
		super();
	}

	public Nation(String nationalCode, String nationalName) {
		super();
		this.nationalCode = nationalCode;
		this.nationalName = nationalName;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public String getNationalName() {
		return nationalName;
	}

	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}

	@Override
	public String toString() {
		return "Nation [nationalCode=" + nationalCode + ", nationalName=" + nationalName + "]";
	}

}
