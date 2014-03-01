package com.loanly.view;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
 
import com.loanly.controller.PlatformManager;
import com.loanly.model.Platform;
 
import com.opensymphony.xwork2.ActionSupport;

 
public class JqPlatformFee extends ActionSupport {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1226104418770649327L;

	/**
	 * 
	 */
    
//    private Double sumAmount;
 
    private PlatformManager platformManager;
    
    private String result;
    private Long platform_id;
 
    public JqPlatformFee() {
    	platformManager = new PlatformManager();
    }
    
    

    @SkipValidation
    public String getPlatformFee() {
    	/*
    	Float fee_rate = this.platformList.get(platform_index).getPlatform_rate();
    	setResult(fee_rate.toString());
    	*/
    	Long platform_idx = getPlatform_id(); 
    	System.out.println("getPlatformFee called " + platform_idx);
    	Platform platform = platformManager.queryByID(platform_idx);
    	System.out.println("platformManager queryByID " + platform.getPlatform_rate());
    	setResult(String.valueOf(platform.getPlatform_rate()));
    	return "getPlatformFee";
    }

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}



	public Long getPlatform_id() {
		return platform_id;
	}



	public void setPlatform_id(Long platform_id) {
		this.platform_id = platform_id;
	}
}