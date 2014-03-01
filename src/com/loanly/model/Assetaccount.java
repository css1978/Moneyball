package com.loanly.model;

import java.io.Serializable;
import java.sql.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="assetaccount")
public class Assetaccount implements Serializable{
     
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -6234752672648322141L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Long account_id;
    private String login_name;
    private String account_name;
    private Integer account_type;
	private double amount;
	private float interest_rate;
    private Date create_date;
    private Integer org_id;
    private Integer order_index;

    @Id
    @GeneratedValue
    @Column(name="account_id")
    public Long getAccount_id() {
        return account_id;
    }
    @Column(name="login_name")
    public String getLogin_name() {
        return login_name;
    }
    @Column(name="account_name")
    public String getAccount_name() {
        return account_name;
    }
    @Column(name="account_type")
    public Integer getAccount_type() {
        return account_type;
    }
    @Column(name="amount")
    public double getAmount() {
        return amount;
    }
    @Column(name="interest_rate")
    public float getInterest_rate() {
        return interest_rate;
    }
    @Column(name="create_date")
    public Date getCreate_date() {
        return create_date;
    }
    @Column(name="org_id")
    public Integer getOrg_id() {
        return org_id;
    }
    @Column(name="order_index")
    public Integer getOrder_index() {
        return order_index;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
    public void setAccount_type(Integer account_type) {
        this.account_type = account_type;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setInterest_rate(float interest_rate) {
        this.interest_rate = interest_rate;
    }
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }    
    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }
    public void setOrder_index(Integer order_index) {
        this.order_index = order_index;
    }
    
}