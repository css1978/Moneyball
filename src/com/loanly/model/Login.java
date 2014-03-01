package com.loanly.model;

import java.io.Serializable;
import java.sql.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="login")
public class Login implements Serializable{
     
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -7125150095827845429L;
	private Long login_id;
    private String login_name;
    private String firstname;
    private String lastname;
    private Integer notified;
    private String mobile;
    private String email;
    private String address;
    private String state;
    private String city;
    private String nation;
    private String logpass;
    private String secpass;
    private Integer active;
    private Date registe_date;
    private Date lastlogin;
    private Date latestmodify;
    private String weixin_no;
    private String logintimes;
    
 
    @Id
    @GeneratedValue
    @Column(name="login_id")
    public Long getLogin_id() {
        return login_id;
    }
    @Column(name="login_name")
    public String getLogin_name() {
        return login_name;
    }
    @Column(name="firstname")
    public String getFirstname() {
        return firstname;
    }
    @Column(name="lastname")
    public String getLastname() {
        return lastname;
    }
    @Column(name="notified")
    public Integer getNotified() {
        return notified;
    }

    @Column(name="mobile")
    public String getMobile() {
        return mobile;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }
    @Column(name="address")
    public String getAddress() {
        return address;
    }
    @Column(name="state")
    public String getState() {
        return state;
    }
    @Column(name="city")
    public String getCity() {
        return city;
    }
    @Column(name="nation")
    public String getNation() {
        return nation;
    }
    @Column(name="logpass")
    public String getLogpass() {
        return logpass;
    }
    @Column(name="secpass")
    public String getSecpass() {
        return secpass;
    }
    @Column(name="active")
    public Integer getActive() {
        return active;
    }
    
    
    @Column(name="lastlogin")
    public Date getLastlogin() {
        return lastlogin;
    }


    public void setLogin_id(Long login_id) {
        this.login_id = login_id;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setNotified(Integer notified) {
        this.notified = notified;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }    
    public void setState(String state) {
        this.state = state;
    }    
    public void setCity(String city) {
        this.city = city;
    }    
    public void setNation(String nation) {
        this.nation = nation;
    }    
    public void setLogpass(String logpass) {
        this.logpass = logpass;
    }    
    public void setSecpass(String secpass) {
        this.secpass = secpass;
    }    
    public void setActive(Integer active) {
        this.active = active;
    }    
    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }    
	public String getWeixin_no() {
		return weixin_no;
	}
	public void setWeixin_no(String weixin_no) {
		this.weixin_no = weixin_no;
	}
	public Date getRegiste_date() {
		return registe_date;
	}
	public void setRegiste_date(Date registe_date) {
		this.registe_date = registe_date;
	}
	public Date getLatestmodify() {
		return latestmodify;
	}
	public void setLatestmodify(Date latestmodify) {
		this.latestmodify = latestmodify;
	}
	public String getLogintimes() {
		return logintimes;
	}
	public void setLogintimes(String logintimes) {
		this.logintimes = logintimes;
	}    
    
}