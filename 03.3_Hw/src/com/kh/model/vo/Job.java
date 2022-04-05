package com.kh.model.vo;

public class Job extends Employee {

	private String jobCode;
	private String jobName;

	public Job() {
		super();
	}

	public Job(String jobCode, String jobName) {
		super();
		this.jobCode = jobCode;
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public String toString() {
		return "Job [jobCode=" + jobCode + ", jobName=" + jobName + "]";
	}

}
