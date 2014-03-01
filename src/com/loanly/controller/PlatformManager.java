package com.loanly.controller;

import java.util.List;
import java.util.Map;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.classic.*;
//import org.hibernate.Session;
 
import com.loanly.model.Platform;
import com.loanly.util.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
 
public class PlatformManager {
 
    public Platform add(Platform platform) {
        Session session = HibernateUtil.getSession();
        
//        platform.setPlatformName("121");
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
    	platform.setLogin_name(user_name);
        session.save(platform);
        
        return platform;
    }

    public Platform update(Platform platform) {
        Session session = HibernateUtil.getSession();
        session.update(platform);
        return platform;
    }
    
    public Platform delete(Long id) {
        Session session = HibernateUtil.getSession();
        
        Platform platform = (Platform) session.load(Platform.class, id);
        if(null != platform) {
            session.delete(platform);
        }
        
        return platform;
    }
 
    public Platform queryByID(Long ID) {
        
        Session session = HibernateUtil.getSession();
        
        Platform platform = null;
        String hql = "from Platform where platform_id = :platform_id";
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("platform_id",ID);
        	platform = (Platform) query.list().get(0);
             
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return platform;
    }

    public List<Platform> list() {
         
        Session session = HibernateUtil.getSession();
        
        List<Platform> platforms = null;
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
        
        String hql = "from Platform where login_name = :login_name";
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
        	platforms = query.list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            
        }
        
        return platforms;
    }
}