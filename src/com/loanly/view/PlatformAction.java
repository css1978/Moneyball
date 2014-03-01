package com.loanly.view;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.loanly.controller.PlatformManager;
import com.loanly.model.Platform;
 
import com.opensymphony.xwork2.ActionSupport;
 
 
public class PlatformAction extends ActionSupport {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2325759296649094711L;
	private Platform platform;
    private List<Platform> platformList;
    private Long platformid;
    private String email;
    private PlatformManager platformManager;
 
    public PlatformAction() {
    	platformManager = new PlatformManager();
    }
 
    @SkipValidation
    public String execute() {
        this.platformList = platformManager.list();
        return SUCCESS;
    }

    @SkipValidation
    public String getrecord() {
        this.platform = platformManager.queryByID(getPlatform_id());
        return SUCCESS;
    }
    
    public String add() {
//        System.out.println(getPlatform().getFirstname());
//    	this.platformList = platformManager.list();
        try {
        	platformManager.add(getPlatform());
            addActionMessage("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，添加失败！");
        }
        this.platformList = platformManager.list();
        return SUCCESS;
    }
    
    public String update() {
        try {
        	platformManager.update(getPlatform());
        	addActionMessage("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，修改失败！");
        }
        return SUCCESS;
    }
 
    
    @SkipValidation
    public String delete() {
        try {
        	platformManager.delete(getPlatform_id());
        	addActionMessage("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，删除失败！");
        }
        return SUCCESS;
    }
 
    public Platform getPlatform() {
    	//System.out.println("platform��firstname == " + platform.getFirstName());
        return platform;
    }
 
    public List<Platform> getPlatformList() {
        return platformList;
    }
 
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
 
    public void setPlatformList(List<Platform> platformsList) {
        this.platformList = platformsList;
    }
 
    public Long getPlatform_id() {
        return platformid;
    }
 
    public void setId(Long platformid) {
        this.platformid = platformid;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}