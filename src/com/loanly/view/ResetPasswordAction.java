package com.loanly.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.loanly.controller.LoginManager;
import com.loanly.controller.UserDAO;
import com.loanly.model.Login;
import com.loanly.util.Encrypt;
import com.loanly.util.GenerateLinkUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.loanly.util.Encrypt;

public class ResetPasswordAction extends ActionSupport  {

	/**
	 * 
	 */
    private Login login;
    private String repassword;

	private static final long serialVersionUID = -1193404172272637097L;
    @SkipValidation
    public String execute() {
        System.out.println("ResetPasswordAction execute called");
        return SUCCESS;
    }
    
    @SkipValidation
    public String verifyresetlink() {
        try {
        	LoginManager loginManager=new LoginManager();
    		HttpServletRequest request= ServletActionContext.getRequest();
        	String username=request.getParameter("userName");
        	Login userInDB = loginManager.getLoginByName(username);
        	if (userInDB==null){
        		addActionError("无此用户！");
        	}
       	 	boolean validCheckCode = GenerateLinkUtils.verifyCheckcode(userInDB, request);
       	 	if(!validCheckCode){
                addActionError("链接失效！");
                return INPUT;       	 	
            }
       	 	setLogin(userInDB);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("密码重置失败！");
            return INPUT;
        }
        return SUCCESS;
    }
    
    
    public String resetpass() {
    	
    	LoginManager loginManager=new LoginManager();
        try {
        	String tmp_password=login.getLogpass();
        	Login userInDB=new Login();
        	userInDB = loginManager.getLoginByName(login.getLogin_name());
        	
        	Encrypt encrypt = new Encrypt();
        	byte[] result = encrypt.desCrypto(tmp_password.getBytes());
        	userInDB.setLogpass(new String(result));
        	loginManager.update(userInDB);
        	addActionMessage("恭喜你，密码重置成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("密码重置失败！");
            return INPUT;
        }
        return SUCCESS;
    }


	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
    

}
