package com.sms.spring.model;

public class AcademicYear {
	private Long acyid;
	private Long acyear;
	private Long no_of_exam;
	private Boolean isclosed;
	
	public Long getAcyid() {
		return acyid;
	}
	public void setAcyid(Long acyid) {
		this.acyid = acyid;
	}
	public Long getAcyear() {
		return acyear;
	}
	public void setAcyear(Long acyear) {
		this.acyear = acyear;
	}
	public Long getNo_of_exam() {
		return no_of_exam;
	}
	public void setNo_of_exam(Long no_of_exam) {
		this.no_of_exam = no_of_exam;
	}
	public Boolean getIsclosed() {
		return isclosed;
	}
	public void setIsclosed(Boolean isclosed) {
		this.isclosed = isclosed;
	}
}
