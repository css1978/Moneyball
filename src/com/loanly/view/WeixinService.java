package com.loanly.view;

import java.util.Date;
import java.util.Map;

import com.loanly.controller.LoginManager;
import com.loanly.model.Login;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WeixinService extends ActionSupport {
	private String wx_id;
	private LoginManager loginManager;
	public String bindWXID() {
		Login login;
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
		
		System.out.println("user_name == " + user_name);
		System.out.println("wxid == " + wx_id);
		
		loginManager = new LoginManager();
		if (user_name!=null && wx_id!=null){
			login = loginManager.getLoginByName(user_name);
			login.setWeixin_no(wx_id);
			try{
				loginManager.update(login);
		    	addActionMessage("绑定成功.");
		        return SUCCESS;
			}
			catch(Exception ex){
				ex.printStackTrace();
		    	addActionMessage("绑定失败.");
		        return INPUT;
			}
		}
		else{
	    	addActionMessage("绑定失败.");
	        return INPUT;
		}
			
	}

	public String unBindWXID() {
		Login login;
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
		
		System.out.println("user_name == " + user_name);
		System.out.println("wxid == " + wx_id);
		
		loginManager = new LoginManager();
		if (user_name!=null && wx_id!=null){
			login = loginManager.getLoginByName(user_name);
			login.setWeixin_no("");
			try{
				loginManager.update(login);
		    	addActionMessage("解绑成功.");
		        return SUCCESS;
			}
			catch(Exception ex){
		    	addActionMessage("解绑失败.");
		        return INPUT;
			}
		}
		else{
	    	addActionMessage("解绑失败.");
	        return INPUT;
		}
			
	}
	public String getWx_id() {
		return wx_id;
	}

	public void setWx_id(String wx_id) {
		this.wx_id = wx_id;
	}
	
}
