package com.loanly.controller;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.loanly.model.Login;
import com.loanly.model.Platform;
import com.loanly.util.Encrypt;
import com.loanly.util.HibernateUtil;
import com.loanly.util.Encrypt;
public class UserDAO {

    public Login attemptLogin(String username,String password) {
        
        Session session = HibernateUtil.getSession();
        
        Login user = null;
        String hql = "from Login where login_name = :login_name and logpass = :logpass";
        
        try {
        	Encrypt encrypt = new Encrypt();
        	byte[] result = encrypt.desCrypto(password.getBytes());
        	/*
        	for(int i=0;i<result.length;i++){
        		System.out.println(result[i]);
        	}
        	*/
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",username);
        	query.setParameter("logpass",new String(result));
        	user = (Login) query.list().get(0);
             
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }
	
	
}
