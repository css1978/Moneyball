package com.loanly.view;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.loanly.controller.LoginManager;
import com.loanly.controller.UserDAO;
import com.loanly.model.Login;
import com.loanly.util.EmailUtils;
import com.loanly.util.Encrypt;
 
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
 
public class RegisterAction extends ActionSupport {
 
    private static final long serialVersionUID = 9149826260758390091L;
    private Login login;
    private List<Login> loginList;
    private Long loginid;
    private String repassword;
    private String oldpassword;
    
    private LoginManager loginManager;
    private Encrypt encrypt;
    public RegisterAction() {
    	loginManager = new LoginManager();
    }
 
    @SkipValidation
    public String execute() {
        this.loginList = loginManager.list();
        System.out.println("LoginAction execute called");
        return SUCCESS;
    }
 
    public String add() {
        Date registe_date = new Date(System.currentTimeMillis());
        try {
        	encrypt = new Encrypt();
        	login = getLogin();
        	login.setActive(1);
        	login.setRegiste_date(registe_date);
        	String tmpPass=login.getLogpass();
        	byte[] result = encrypt.desCrypto(tmpPass.getBytes());
        	login.setLogpass(new String(result));
        	loginManager.add(login);
        	addActionMessage("注册成功，请用注册用户登录开启您的财务自由之路！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，注册失败，请添加站长微信反映问题！");
            return INPUT;
        }
        return SUCCESS;
    }
    @SkipValidation
    public String getrecord() {
        try {
        	Map attibutes = (Map) ActionContext.getContext().getSession(); 
        	String user_name = attibutes.get("username").toString();
        	
            this.login = loginManager.getLoginByName(user_name);
        	
        } catch (Exception e) {
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }

    
    
    public String modifyInfo() {
        try {
        	String email= null;
        	String mobile= null;
        	String firstname= null;
        	email = login.getEmail();
        	mobile = login.getMobile();
        	firstname=login.getFirstname();
        	
        	login= loginManager.getLoginByName(login.getLogin_name());
        	login.setEmail(email);
        	login.setMobile(mobile);
        	login.setFirstname(firstname);
        	
        	loginManager.update(login);
        	addActionMessage("恭喜你，修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("很不幸，修改失败！");
        }
        return SUCCESS;
    }

    public String modifypass() {
    	UserDAO userDao = new UserDAO();
    	
    	if (oldpassword==null || oldpassword.isEmpty()){
            addActionError("原密码不能为空！");
            return INPUT;
    	}
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();

    	
        try {
        	Login user = userDao.attemptLogin(user_name, oldpassword);
        	if (user==null){
                addActionError("原密码错误，请重新输入！");
                return INPUT;
        	}
        	String tmp_password=login.getLogpass();
        	
        	encrypt = new Encrypt();
        	byte[] result = encrypt.desCrypto(tmp_password.getBytes());
        	user.setLogpass(new String(result));
        	loginManager.update(user);
        	addActionMessage("恭喜你，修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("原密码错误，请重新输入！");
            return INPUT;
        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String forgotpass() {
    	String serverURL=getText("server.url");
    	String emailfrom=getText("passwordreset.email.name");
    	String emailpass=getText("passwordreset.email.password");
    	String emailserver=getText("passwordreset.email.url");
        try {
        	Login userInDB=new Login();
        	userInDB=loginManager.getLoginByName(login.getLogin_name());
        	//验证用户名和邮箱是否存在
        	if (!userInDB.getEmail().equals(login.getEmail())){
        		addActionError("该用户注册时的邮箱不是当前输入的邮箱地址，请核对！");
        		return INPUT;
        	}
        	EmailUtils.sendResetPasswordEmail(userInDB,serverURL,emailfrom,emailpass,emailserver);
        	addActionMessage("重置密码邮件已发送到您注册邮箱，请登录邮箱点击链接重置密码！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("没有输入用户或者注册信箱错误！");
            return INPUT;
        }
        return SUCCESS;
    }
    
    
    @SkipValidation
    public String delete() {
    	try{
        	loginManager.delete(getLoginId());
            return SUCCESS;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		return INPUT;
    	}
    }
 
    public Login getLogin() {
    	//System.out.println("login��firstname == " + login.getFirstName());
        return login;
    }
 
    public List<Login> getLoginList() {
        return loginList;
    }
 
    public void setLogin(Login login) {
        this.login = login;
    }
 
    public void setLoginList(List<Login> loginsList) {
        this.loginList = loginsList;
    }
 
    public Long getLoginId() {
        return loginid;
    }
 
    public void setId(Long loginid) {
        this.loginid = loginid;
    }

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
}