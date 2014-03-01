package com.loanly.controller;

import java.util.List;





import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.classic.*;

import com.loanly.model.Loan_detail;
import com.loanly.model.Login;
import com.loanly.util.HibernateUtil;
 
public class LoginManager {
 
    public Login add(Login login) throws Exception {
    	try{
            Session session = HibernateUtil.getSession();
            
//          login.setLoginName("121");
          session.save(login);
    	}
    	catch(Exception ex){
    		throw ex;
    	}
        return login;
    }
    public Login delete(Long id) throws Exception {
    	try{
            Session session = HibernateUtil.getSession();
            Login login = (Login) session.load(Login.class, id);
            if(null != login) {
                session.delete(login);
            }
            return login;
    	}
    	catch(Exception ex){
    		throw ex;
    	}
    }
 
    public Login getLoginByName(String login_name) {
        Session session = HibernateUtil.getSession();
        Login login = null;
        
        String hql = "from Login where login_name= :login_name";
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",login_name);
        	List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return null;
            }
            login = (Login) li.get(0);
        	//System.out.println("loan_details 1 == " + loan_details.get(0).getInterest_amt());
        } catch (HibernateException e) {
            
            e.printStackTrace();
            throw e;
        }
        return login;
    }
    
    public Boolean verifyPass(String username,String password) {
        Session session = HibernateUtil.getSession();
        String hql = "select count(*) from Login where active=1 and login_name = :login_name and logpass = :password";
        
        //session.beginTransaction();
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",username);
        	query.setParameter("password",password);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return false;
            }else{
            	System.out.println("record == " + li.get(0));
            	if (li.get(0) == null){
            		return false;
            	}
            	if ( Integer.parseInt(li.get(0).toString()) == 0){
            		return false;
            	}
            }
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        	return false;
        }
        return true;
    }

    public Login update(Login login) throws Exception {
    	try{
            Session session = HibernateUtil.getSession();
            session.update(login);
    	}
    	catch(Exception ex){
    		throw ex;
    	}
        
        return login;
    }
       

    public List<Login> list() {
         
        Session session = HibernateUtil.getSession();
        
        List<Login> logins = null;
        try {
             
        	logins = (List<Login>)session.createQuery("from Login").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
        
        return logins;
    }
}