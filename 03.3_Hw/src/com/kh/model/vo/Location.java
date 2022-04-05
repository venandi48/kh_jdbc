package com.kh.model.vo;

public class Location extends Department {

	private String localCode;
	private String nationalCode;
	private String localName;

	public Location() {
		super();
	}

	public Location(String localCode, String nationalCode, String localName) {
		super();
		this.localCode = localCode;
		this.nationalCode = nationalCode;
		this.localName = localName;
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public String toString() {
		return "Location [localCode=" + localCode + ", nationalCode=" + nationalCode + ", localName=" + localName + "]";
	}

}
