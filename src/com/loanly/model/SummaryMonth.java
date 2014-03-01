package com.loanly.model;

public class SummaryMonth {
	private String monthVal;
	private Double ownAmt;
	private Double insAmt;
	private Double awardAmt;
	public String getMonthVal() {
		return monthVal;
	}
	public void setMonthVal(String monthVal) {
		this.monthVal = monthVal;
	}
	public Double getOwnAmt() {
		return ownAmt;
	}
	public void setOwnAmt(Double ownAmt) {
		this.ownAmt = ownAmt;
	}
	public Double getInsAmt() {
		return insAmt;
	}
	public void setInsAmt(Double insAmt) {
		this.insAmt = insAmt;
	}
	public Double getAwardAmt() {
		return awardAmt;
	}
	public void setAwardAmt(Double awardAmt) {
		this.awardAmt = awardAmt;
	}
}
