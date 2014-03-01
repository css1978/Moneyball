package com.loanly.model;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="loan_detail")
public class Loan_detail implements Serializable{
     
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -6123959676371032057L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Long record_id;
//	private Long loansid;
//	private Long platform_id;
	private String login_name;
	private int period;
	private int total_period;
	private double interest_amt;
	private double own_amt;
	private double fee_amt;
	private float interest_rate;
	private Date loan_date;
	private Date expire_date;
	private Integer status;
	private Date return_date;
	private double late_charge;
	private String comments;
	private Platform platform;
	private Loan loan;
	
    @Id
    @GeneratedValue
    @Column(name="record_id")
    public Long getRecord_id() {
        return record_id;
    }
    @Column(name="login_name")
    public String getLogin_name() {
        return login_name;
    }
/*
 *     @Column(name="platform_id")
 
    public Long getPlatform_id() {
        return platform_id;
    }
*/	@Column(name="interest_amt")
	public double getInterest_amt() {
		return interest_amt;
	}
	@Column(name="own_amt")
	public double getOwn_amt() {
		return own_amt;
	}
	@Column(name="loan_date")
	public Date getLoan_date() {
		return loan_date;
	}
	@Column(name="expire_date")
	public Date getExpire_date() {
		return expire_date;
	}
    
	@Column(name="status")
    public Integer getStatus() {
        return status;
    }
	@Column(name="return_date")
	public Date getReturn_date() {
		return return_date;
	}
	@Column(name="late_charge")
	public double getLate_charge() {
		return late_charge;
	}
	@Column(name="comments")
    public String getComments() {
        return comments;
    }
/*	
    public void setPlatform_id(Long platform_id) {
        this.platform_id = platform_id;
    }
   */
    public void setStatus(Integer status) {
        this.status = status;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
	public void setInterest_amt(double interest_amt) {
		this.interest_amt = interest_amt;
	}
	public void setLoan_date(Date loan_date) {
		this.loan_date = loan_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public void setRecord_id(Long record_id) {
		this.record_id = record_id;
	}
	public void setOwn_amt(double own_amt) {
		this.own_amt = own_amt;
	}
	public void setExpire_date(Date date) {
		this.expire_date = date;
	}
	public void setLate_charge(double late_charge) {
		this.late_charge = late_charge;
	}
/*
	public Long getLoansid() {
		return loansid;
	}
	public void setLoansid(Long loansid) {
		this.loansid = loansid;
	}
*/
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int i) {
		this.period = i;
	}
	public int getTotal_period() {
		return total_period;
	}
	public void setTotal_period(int total_period) {
		this.total_period = total_period;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public double getFee_amt() {
		return fee_amt;
	}
	public void setFee_amt(double fee_amt) {
		this.fee_amt = fee_amt;
	}
	public float getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(float interest_rate) {
		this.interest_rate = interest_rate;
	}
    
}