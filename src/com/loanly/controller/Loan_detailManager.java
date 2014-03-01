package com.loanly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.classic.*;
//import org.hibernate.Session;
 
import com.loanly.model.Loan_detail;

import com.loanly.util.HibernateUtil;
 
public class Loan_detailManager{ 
    public Loan_detail add(Loan_detail loan_detail) {
        Session session = HibernateUtil.getSession();
        
//        login.setLoginName("121");
        session.save(loan_detail);
        
        return loan_detail;
    }
    public Loan_detail delete(Long id) {
        Session session = HibernateUtil.getSession();
        
        Loan_detail loan_detail = (Loan_detail) session.load(Loan_detail.class, id);
        if(null != loan_detail) {
            session.delete(loan_detail);
        }
        
        return loan_detail;
    }
 
    public Loan_detail update(Loan_detail loan_detail) {
        Session session = HibernateUtil.getSession();
        
//        login.setLoginName("121");
        session.update(loan_detail);
        
        return loan_detail;
    }

    public Loan_detail getDetailByID(Long loan_detail_id) {
        Session session = HibernateUtil.getSession();
        Loan_detail loan_detail = null;
        
        String hql = "from Loan_detail where record_id= :record_id";
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("record_id",loan_detail_id);
        	List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return null;
            }
            loan_detail = (Loan_detail) li.get(0);
        	//System.out.println("loan_details 1 == " + loan_details.get(0).getInterest_amt());
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return loan_detail;
    }
    
    //到期未还款的list
    public List<Loan_detail> getReturnList(String username,Date dueDate) {
         
        Session session = HibernateUtil.getSession();
        
        List<Loan_detail> loan_details = null;
        String hql = "from Loan_detail where status=0 and login_name = :login_name and expire_date<= :dueDate order by expire_date desc";
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",username);
        	query.setParameter("dueDate",dueDate);
        	loan_details = query.list();
        	//System.out.println("loan_details 1 == " + loan_details.get(0).getInterest_amt());
            
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return loan_details;
    }

    //所有借出的list
    public List<Loan_detail> list(String username,Date expireStartDate,Date expireEndDate) {
        
        Session session = HibernateUtil.getSession();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        List<Loan_detail> loan_details = null;
        String hql = "from Loan_detail where login_name = :login_name ";
        if (expireStartDate != null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d')>=" + f.format(expireStartDate);
        }
        if  (expireEndDate!=null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d')<=" + f.format(expireEndDate);
        }
        hql += " order by expire_date desc";
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",username);
        	loan_details = query.list();
        	//System.out.println("loan_details 1 == " + loan_details.get(0).getInterest_amt());
            
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return loan_details;
    }
}

