package com.loanly.view;

import java.util.List;

import java.util.Map;

import com.loanly.controller.AssetaccountManager;
import com.loanly.model.Assetaccount;
 
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
 
public class AssetaccountAction extends ActionSupport {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 3145218673797568556L;
	/**
	 * 
	 */
	private Assetaccount assetaccount;
    private List<Assetaccount> assetaccountList;
    private Long assetaccountid;
 
    private AssetaccountManager assetaccountManager;
 
    public AssetaccountAction() {
    	assetaccountManager = new AssetaccountManager();
    }
 
    public String execute() {
        this.assetaccountList = assetaccountManager.list();
        System.out.println("AssetaccountAction execute called");
        return SUCCESS;
    }
 
    public String add() {
//        System.out.println(getAssetaccount().getActive());
//        System.out.println(getAssetaccount().getFirstname());
        try {
        	assetaccountManager.add(getAssetaccount());
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.assetaccountList = assetaccountManager.list();
        return SUCCESS;
    }
 
    public String delete() {
    	assetaccountManager.delete(getAssetaccount_id());
        return SUCCESS;
    }
 
    public Assetaccount getAssetaccount() {
    	//System.out.println("assetaccount��firstname == " + assetaccount.getFirstName());
        return assetaccount;
    }
 
    public List<Assetaccount> getAssetaccountList() {
        return assetaccountList;
    }
 
    public void setAssetaccount(Assetaccount assetaccount) {
        this.assetaccount = assetaccount;
    }
 
    public void setAssetaccountList(List<Assetaccount> assetaccountsList) {
        this.assetaccountList = assetaccountsList;
    }
 
    public Long getAssetaccount_id() {
        return assetaccountid;
    }
 
    public void setId(Long assetaccountid) {
        this.assetaccountid = assetaccountid;
    }
    
}