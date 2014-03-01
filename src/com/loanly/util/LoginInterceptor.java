package com.loanly.util;



import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.loanly.controller.LoginManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class LoginInterceptor extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7038099286517897023L;
	/**
	 * 
	 */
	public static final String USER_SESSION_KEY="loanly.session.user";
	public static final String COOKIE_REMEMBERME_KEY="loanly.cookie.rememberme";
	public static final String GOING_TO_URL_KEY="GOING_TO";
	public static final String GOING_TO_QUERY_KEY="GOING_QUERY";
	
	private LoginManager loginManager;


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		String requestString = request.getQueryString();
		Map session = actionContext.getSession();
		
		//如下的action不需要验证用户是否登录，struts.xml里面的配置不知道为啥不起作用
		if (invocation.getProxy().getActionName().toString().equals("register_req") || invocation.getProxy().getActionName().toString().equals("loginadd")
				|| invocation.getProxy().getActionName().toString().equals("WeixinFocus") || invocation.getProxy().getActionName().toString().equals("logout")
				|| invocation.getProxy().getActionName().toString().equals("forgotpass") || invocation.getProxy().getActionName().toString().equals("resetPasswordUI")
				|| invocation.getProxy().getActionName().toString().equals("verify_succ") || invocation.getProxy().getActionName().toString().equals("resetpass")
				){
			if (invocation.getProxy().getActionName().toString().equals("logout")){
				setGoingToURL(session, invocation,requestString);
			}
			else{
				setGoingToURL(session, invocation,requestString);
			}
			return invocation.invoke();
		}

		//System.out.println("request.getRequestURI()"+request.getRequestURI());
		//System.out.println("request.getQueryString()"+request.getQueryString());
		if (session != null && session.get(USER_SESSION_KEY) != null){
			return invocation.invoke();
		}
		if (requestString!=null){
			setGoingToQeury(session, invocation,requestString);
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split("==");
						String userName = split[0];
						String password = split[1];
						try {
							Boolean loginSuccess = loginManager.verifyPass(userName, password);
							if (loginSuccess==true){
								session.put(USER_SESSION_KEY, userName);
							}
							else{
//								ActionSupport.addActionError(getText("error.login"));
								return "login";
							}
						} catch (Exception e) {
							setGoingToURL(session, invocation,requestString);
							return "login";
						}
					} else {
						setGoingToURL(session, invocation,requestString);
						return "login";
					}
					return invocation.invoke();
				}
			}
		}
		setGoingToURL(session, invocation,requestString);
		return "login";

	}
	private void setGoingToQeury(Map session, ActionInvocation invocation,String requestString){
		String url = "";
		url += "?" + requestString;
		session.put(GOING_TO_QUERY_KEY, url);
	}

	
	private void setGoingToURL(Map session, ActionInvocation invocation,String requestString){
		String url = "";
		String namespace = invocation.getProxy().getNamespace();
		if (StringUtils.isNotBlank(namespace) && !namespace.equals("/")){
			url = url + namespace;
		}
		String actionName = invocation.getProxy().getActionName();
		//如下的链接不需要保留转发
		if (StringUtils.isNotBlank(actionName) && !actionName.equals("logout") && !actionName.equals("register_req") && !actionName.equals("loginadd") && !actionName.equals("forgotpass")
				&& !actionName.equals("resetpass")){
			url = url + "/" + actionName + ".action";
			if (requestString!=null){
				url += "?" + requestString;
			}
		}
		session.put(GOING_TO_URL_KEY, url);
	}
	

}