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
@Table(name="platform")
public class Platform implements Serializable{
     
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 7222431812658568992L;
	/**
	 * 
	 */
	private Long platform_id;
    private String platform_name;
    private String login_name;
    private String platform_url;
	private float platform_rate;
    private Integer active;
    private Date online_time;
    private Integer offline;
	private float delegate_rate;
	private Set<Loan_detail> loan_details;
	
	
    @Id
    @GeneratedValue
    @Column(name="platform_id")
    public Long getPlatform_id() {
        return platform_id;
    }
    @Column(name="platform_name")
    public String getPlatform_name() {
        return platform_name;
    }
    @Column(name="platform_url")
    public String getPlatform_url() {
        return platform_url;
    }
    @Column(name="platform_rate")
    public float getPlatform_rate() {
        return platform_rate;
    }
    @Column(name="active")
    public Integer getActive() {
        return active;
    }
    @Column(name="online_time")
    public Date getOnline_time() {
        return online_time;
    }
    @Column(name="offline")
    public Integer getOffline() {
        return offline;
    }
    @Column(name="delegate_rate")
    public float getDelegate_rate() {
        return delegate_rate;
    }
    public void setPlatform_id(Long platform_id) {
        this.platform_id = platform_id;
    }
    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }
    public void setPlatform_url(String platform_url) {
        this.platform_url = platform_url;
    }
    public void setPlatform_rate(float platform_rate) {
        this.platform_rate = platform_rate;
    }
    public void setOffline(Integer offline) {
        this.offline = offline;
    }
    public void setActive(Integer active) {
        this.active = active;
    }    
    public void setDelegate_rate(float delegate_rate) {
        this.delegate_rate = delegate_rate;
    }
    public void setOnline_time(java.sql.Date online_time) {
        this.online_time = online_time;
    }
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public Set<Loan_detail> getLoan_details() {
		return loan_details;
	}
	public void setLoan_details(Set<Loan_detail> loan_details) {
		this.loan_details = loan_details;
	}    
    
}