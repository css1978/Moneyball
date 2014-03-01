package com.loanly.util;
 
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.loanly.controller.LoginManager;
import com.loanly.controller.SummaryManager;
import com.loanly.controller.UserDAO;
import com.loanly.model.Login;
import com.loanly.view.SummaryAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.commons.lang.StringUtils;

public class LoginAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware, CookiesAware{
/**
	 * 
	 */
	private static final long serialVersionUID = 5440983946013594048L;
	//public class LoginAction {
    /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	private String loginName;
	private String password;
	private String loginkeeping;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map session;
	private Map cookies;
	private String goingToURL;
	public String getGoingToURL() {
		return goingToURL;
	}
	public void setGoingToURL(String goingToURL) {
		this.goingToURL = goingToURL;
	}
	public String isRememberMe() {
		return loginkeeping;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@SkipValidation
	public String login()throws Exception{
		try {
			System.out.println("loginName == " + loginName);
			System.out.println("password == " + password);
			UserDAO userDao = new UserDAO();
			Login user = userDao.attemptLogin(loginName, password);
			System.out.println("user == " + user.getLogin_name());

			if (loginkeeping != null && loginkeeping.equals("loginkeeping")){
				Cookie cookie = new Cookie(LoginInterceptor.COOKIE_REMEMBERME_KEY, loginName+ "==" + password );
				cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
			}
			session.put(LoginInterceptor.USER_SESSION_KEY, user);
            Map attibutes = ActionContext.getContext().getSession(); 
            attibutes.put("username", loginName);
            
			String goingToURL = (String) session.get(LoginInterceptor.GOING_TO_URL_KEY);
			if (StringUtils.isNotBlank(goingToURL)){
				setGoingToURL(goingToURL);
				session.remove(LoginInterceptor.GOING_TO_URL_KEY);
			}else{
				setGoingToURL("index.action");
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("用户名或密码错误.");
			return INPUT;
		}
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public void setCookiesMap(Map cookies) {
		this.cookies = cookies;
	}
	public String getLoginkeeping() {
		return loginkeeping;
	}
	public void setLoginkeeping(String loginkeeping) {
		this.loginkeeping = loginkeeping;
	}

}