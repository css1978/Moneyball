package com.loanly.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.classic.*;
//import org.hibernate.Session;
 
import com.loanly.model.Loan;
import com.loanly.model.Loan_detail;
import com.loanly.model.Platform;
import com.loanly.util.CalInsAmt;
import com.loanly.util.HibernateUtil;
import com.loanly.util.SplitLoanDtl;
import com.opensymphony.xwork2.ActionContext;
 
public class LoanManager {
 
    public Loan add(Loan loan) {
    	List<Loan_detail> loan_details=null;
    	Set<Loan_detail> setLoan_details=null;
    	//Loan_detail loan_detail=null;
    	SplitLoanDtl splitLoanDtl = new SplitLoanDtl();
        Session session = HibernateUtil.getSession();
//        loan.setLoanName("121");
        //用户名
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
    	loan.setLogin_name(user_name);
        
//    	loan.getPlatform().setPlatform_id(platform_id)
    	
        //状态 0--正在还款
    	loan.setStatus(0);  
        
    	
        //利息金额
    	CalInsAmt calInsAmt = new CalInsAmt();
        double ins_amt;
        float ins_rate = loan.getInterest_rate();
        int ins_rate_type = loan.getInterest_rate_type();
        int duration = loan.getDuration();
        int daily = loan.getDaily();
        //int sec = loan.getSecond();
        int sec = 0;
        int return_type = loan.getReturn_type();
        double loan_amt = loan.getAmount();
        ins_amt = calInsAmt.proceed(loan_amt,ins_rate, ins_rate_type, duration, daily, sec, return_type);
        loan.setInterest_amt(ins_amt);
        	
        //续投奖励金额
        float continue_rate = loan.getContinue_rate();
        double continue_amt = loan_amt * continue_rate/100;
        loan.setContinue_amt(continue_amt);

        //投标奖励金额
        float award_rate = loan.getAward_rate();
        double award_amt = loan_amt * award_rate/100;
        loan.setAward_amt(award_amt);
        //费用金额
        float fee_rate = loan.getFee_rate();
        double fee_amt = ins_amt * fee_rate/100;
        loan.setFee_amt(fee_amt);
        
        //线下奖励金额
        float offline_rate = loan.getOffline_rate();
        double offline_amt = loan_amt * offline_rate/100;
        loan.setOffline_amt(offline_amt);
        
        //还款日期
        session.save(loan);
        

        //根据还款类型拆分明细
        loan_details = splitLoanDtl.proceed(loan);
//        setLoan_details = new HashSet(loan_details);
        for(int i=0;i<loan_details.size();i++)
        {
        	Loan_detail loan_detail = new Loan_detail();
        	loan_detail = loan_details.get(i);
        	
        	//setLoan_details.add(loan_details.get(i));
        	//System.out.println("loan_detail.getInterest_amt() == " + loan_detail.getInterest_amt());
        	//System.out.println("loan_detail.getExpire_date() == " + loan_detail.getExpire_date());
        	session.save(loan_detail);
        }
        //loan.setLoan_details(setLoan_details);
        return loan;
    }
    
    public Loan update(Loan loan) {
        Session session = HibernateUtil.getSession();
        session.update(loan);
        return loan;
    }
    
    
    public Loan queryByID(Long ID) {
        
        Session session = HibernateUtil.getSession();
        
        Loan loan = null;
        String hql = "from Loan where loansid = :loansid";
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("loansid",ID);
        	loan = (Loan) query.list().get(0);
             
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return loan;
    }

    
    public Loan delete(Long id) {
        Session session = HibernateUtil.getSession();
        
        Loan loan = (Loan) session.load(Loan.class, id);
        if(null != loan) {
            session.delete(loan);
        }
        
        return loan;
    }
 
    public List<Loan> list(Date loanStartDate,Date loanEndDate) {
         
    	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
    	
        Session session = HibernateUtil.getSession();
        
        List<Loan> loans = null;
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
    	String hql = "from Loan where login_name = :login_name";
        if (loanStartDate != null){
        	hql += " and DATE_FORMAT(loan_date,'%Y%m%d')>=" + f.format(loanStartDate);
        }
        if  (loanEndDate!=null){
        	hql += " and DATE_FORMAT(loan_date,'%Y%m%d')<=" + f.format(loanEndDate);
        }
        hql += " order by loan_date desc";
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
        	loans = query.list();
            
             
        } catch (HibernateException e) {
            e.printStackTrace();
            
        }
        return loans;
    }

}