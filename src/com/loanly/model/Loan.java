package com.loanly.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

 
@Entity
@Table(name="loan")
public class Loan implements Serializable{
     
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -9221810274228215618L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Long loansid;
	//private Long platform_id;
	private String login_name;
	private double amount;
	private float interest_rate;
	private Integer interest_rate_type;
	private float award_rate;
	private float continue_rate;
	private float offline_rate;
	private float fee_rate;
	private Integer daily;
	private Integer second;
	private Integer duration;
	private Integer loan_type;
	private float delegate_rate;
    private java.sql.Date loan_date;
	private Integer return_type;
	private Integer status;
    private String comments;
	private Integer money_from;
	private Integer interest_type;
	private Integer money_to;
	private double award_amt;
	private double interest_amt;
	private double continue_amt;
	private double fee_amt;
	private double offline_amt;
    private java.sql.Date return_date;
	private Platform platform;
	private Set<Loan_detail> loan_details;
	
    @Id
    @GeneratedValue
    @Column(name="loansid")
    public Long getLoansid() {
        return loansid;
    }
    
    @Column(name="login_name")
    public String getLogin_name() {
        return login_name;
    }
    
/*    
    @Column(name="platform_id")
    public Long getPlatform_id() {
        return platform_id;
    }
*/
    @Column(name="amount")
    public Double getAmount() {
        return amount;
    }

    @Column(name="interest_rate")
    public float getInterest_rate() {
        return interest_rate;
    }
	
    @Column(name="interest_rate_type")
    public Integer getInterest_rate_type() {
        return interest_rate_type;
    }

	@Column(name="award_rate")
    public float getAward_rate() {
        return award_rate;
    }

	@Column(name="continue_rate")
    public float getContinue_rate() {
        return continue_rate;
    }

    @Column(name="offline_rate")
    public float getOffline_rate() {
        return offline_rate;
    }

	@Column(name="fee_rate")
    public float getFee_rate() {
        return fee_rate;
    }

    @Column(name="daily")
    public Integer getDaily() {
        return daily;
    }
	
    @Column(name="second")
    public Integer getSecond() {
        return second;
    }

    @Column(name="duration")
    public int getDuration() {
        return duration;
    }

	@Column(name="loan_type")
    public Integer getLoan_type() {
        return loan_type;
    }

	@Column(name="delegate_rate")
    public float getDelegate_rate() {
        return delegate_rate;
    }
	
	@Column(name="loan_date")
    public Date getLoan_date() {
        return loan_date;
    }
	
	@Column(name="return_type")
    public Integer getReturn_type() {
        return return_type;
    }

	@Column(name="status")
    public Integer getStatus() {
        return status;
    }

	@Column(name="comments")
    public String getComments() {
        return comments;
    }

	@Column(name="money_from")
    public Integer getMoney_from() {
        return money_from;
    }

	@Column(name="money_to")
    public Integer getMoney_to() {
        return money_to;
    }

	@Column(name="interest_amt")
	public double getInterest_amt() {
		return interest_amt;
	}
	@Column(name="continue_amt")
	public double getContinue_amt() {
		return continue_amt;
	}
	@Column(name="fee_amt")
	public double getFee_amt() {
		return fee_amt;
	}
	@Column(name="offline_amt")
	public double getOffline_amt() {
		return offline_amt;
	}
	@Column(name="return_date")
	public Date getReturn_date() {
		return return_date;
	}
	
    public void setLoansid(Long loansid) {
        this.loansid = loansid;
    }
/*
    public void setPlatform_id(Long platform_id) {
        this.platform_id = this.platform.getPlatform_id();
        System.out.println("this.platform_id == " + this.platform_id);
    }
*/

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public void setInterest_rate(float interest_rate) {
        this.interest_rate = interest_rate;
    }

    public void setInterest_rate_type(Integer interest_rate_type) {
        this.interest_rate_type = interest_rate_type;
    }
	
    public void setAward_rate(float award_rate) {
        this.award_rate = award_rate;
    }

    public void setOffline_rate(float offline_rate) {
        this.offline_rate = offline_rate;
    }

    public void setContinue_rate(float continue_rate) {
        this.continue_rate = continue_rate;
    }

    public void setFee_rate(float fee_rate) {
        this.fee_rate = fee_rate;
    }


    public void setDaily(Integer daily) {
        this.daily = daily;
    }
	

    public void setSecond(Integer second) {
        this.second = second;
    }


    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public void setLoan_type(Integer loan_type) {
        this.loan_type = loan_type;
    }


    public void setDelegate_rate(Float delegate_rate) {
        this.delegate_rate = delegate_rate;
    }
	

    public void setLoan_date(java.sql.Date loan_date) {
        this.loan_date = loan_date;
    }
	

    public void setReturn_type(Integer return_type) {
        this.return_type = return_type;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }


    public void setMoney_from(Integer money_from) {
        this.money_from = money_from;
    }


    public void setMoney_to(Integer money_to) {
        this.money_to = money_to;
    }
	public void setInterest_amt(double interest_amt) {
		this.interest_amt = interest_amt;
	}
	public void setContinue_amt(double continue_amt) {
		this.continue_amt = continue_amt;
	}
	public void setFee_amt(double fee_amt) {
		this.fee_amt = fee_amt;
	}
	public void setOffline_amt(double offline_amt) {
		this.offline_amt = offline_amt;
	}
	public void setReturn_date(java.sql.Date return_date) {
		this.return_date = return_date;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Integer getInterest_type() {
		return interest_type;
	}

	public void setInterest_type(Integer interest_type) {
		this.interest_type = interest_type;
	}

	public Set<Loan_detail> getLoan_details() {
		return loan_details;
	}

	public void setLoan_details(Set<Loan_detail> loan_details) {
		this.loan_details = loan_details;
	}

	public double getAward_amt() {
		return award_amt;
	}

	public void setAward_amt(double award_amt) {
		this.award_amt = award_amt;
	}

    
}