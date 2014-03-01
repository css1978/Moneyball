package com.loanly.controller;

import java.util.List;
import java.util.Map;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.classic.*;
//import org.hibernate.Session;
 
import com.loanly.model.Assetaccount;
import com.loanly.util.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
 
public class AssetaccountManager {
 
    public Assetaccount add(Assetaccount assetaccount) {
        Session session = HibernateUtil.getSession();
        
//        assetaccount.setAssetaccountName("121");
    	System.out.println("assetaccount == " + assetaccount.getAccount_name());
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	
    	System.out.println("username == " + attibutes.get("username"));
    	assetaccount.setLogin_name(attibutes.get("username").toString());
        
        session.save(assetaccount);
        
        return assetaccount;
    }
    public Assetaccount delete(Long id) {
        Session session = HibernateUtil.getSession();
        
        Assetaccount assetaccount = (Assetaccount) session.load(Assetaccount.class, id);
        if(null != assetaccount) {
            session.delete(assetaccount);
        }
        
        return assetaccount;
    }
 
    public List<Assetaccount> list() {
         
        Session session = HibernateUtil.getSession();
        
        List<Assetaccount> assetaccounts = null;
        try {
             
        	assetaccounts = (List<Assetaccount>)session.createQuery("from Assetaccount").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            
        }
        
        return assetaccounts;
    }
}