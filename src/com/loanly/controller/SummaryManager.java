package com.loanly.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat; 
import java.util.List;
import java.util.Map;









import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.classic.*;

import com.loanly.model.SummaryMonth;
//import org.hibernate.Session;
import com.loanly.model.SummaryPlatform; 
import com.loanly.util.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
 
public class SummaryManager  {
 
//总借出
    private Double sumAmount;
    private Double assetAmount;
    private Double debitAmount;
    private Double curr_month_interest;
    private Double curr_month_award;
    private Double total_interest;
    private Double total_award;
    
    private Double dueAllAmount;
    private Double dueAllOwn;
    private Double dueAllIns;
    private float dueAllRate;
    private List<SummaryPlatform> summaryPlatform_list;
    private List<SummaryMonth> summaryMonth_list;
    
	//private Session session = HibernateUtil.getSession();

	private String current_yearmonth;
	private String current_year;
	private String current_month;
	
	public SummaryManager(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
    	Date now = new Date();
    	
    	current_yearmonth = df.format(now);
    	current_year = current_yearmonth.substring(0, 4);
    	current_month = current_yearmonth.substring(4,6);
    	//user_name = getUser_name();
	}
	//借出总额
    public double getSumAmount(String user_name) {
        
        Session session = HibernateUtil.getSession();
    	//session = HibernateUtil.getSession();
        sumAmount = (double) 0;
        String hql = "select sum(amount) from Loan where login_name = :login_name";
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            sumAmount  =(Double) li.get(0);
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return sumAmount;
    }
    
    //assetamount = 资产总额=账户金额 + 借出待收金额
    public double getAssetAmount(String user_name) {
        assetAmount = (double) 0;
        //tempAmt = getDueAmount();
        Session session = HibernateUtil.getSession();
        //session = HibernateUtil.getSession();
        String hql = "select sum(amount) from Assetaccount where login_name = :login_name";
        //where account_type in (0,2,3) and login_name = :login_name
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            double tmpAmount  =(Double) li.get(0);
        	assetAmount = assetAmount + tmpAmount;
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return assetAmount;
    }

  //debitamount = 总负债
    public double getDebitAmount(String user_name) {
        
        Session session = HibernateUtil.getSession();
        debitAmount = (double) 0;
        String hql = "select sum(amount) from Assetaccount where account_type=1 and login_name = :login_name";
    	//session = HibernateUtil.getSession();
        //session.beginTransaction().begin();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            debitAmount =(Double) li.get(0);
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return debitAmount;
    }
    
  //净资产=总资产-负债 不需要从数据库计算
    
    //当月已赚利息 - 手续费
    public double getCurr_Month_Interest(String user_name) {
        
        Session session = HibernateUtil.getSession();
        curr_month_interest = (double) 0;
        String hql = "select sum(interest_amt-fee_amt) from Loan_detail where status=1 and year(expire_date) = :current_year and month(expire_date) = :current_month and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
        	query.setParameter("current_year",Integer.parseInt(current_year));
        	query.setParameter("current_month",Integer.parseInt(current_month));
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            curr_month_interest =(Double) li.get(0);
             
        } catch (HibernateException e) {
            e.printStackTrace();
            
        }
        return curr_month_interest;
    }

    
  //当月奖励
    public double getCurr_Month_Award(String user_name) {
        
        Session session = HibernateUtil.getSession();
        curr_month_award = (double) 0;
//计算奖励金额
    	String hql = "select sum(award_amt+continue_amt+offline_amt) from Loan where year(loan_date) = :current_year and month(loan_date) = :current_month and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
        	query.setParameter("current_year",Integer.parseInt(current_year));
        	query.setParameter("current_month",Integer.parseInt(current_month));
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            curr_month_award =(Double) li.get(0);
//        	System.out.println("curr_month_award ==" + curr_month_award);
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        
        return curr_month_award;
    }    
    
    //当月收益 利息+奖励 不需要从数据库读取    

    //总收益 利息+奖励 不需要从数据库读取
    
  //总利息
    public double getTotal_Interest(String user_name) {
        
        Session session = HibernateUtil.getSession();
        total_interest = (double) 0;
        String hql = "select sum(interest_amt-fee_amt) from Loan_detail where status=1 and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            total_interest =(Double) li.get(0);
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return total_interest;
    }
    
  //总奖励 不需要考虑是否已回款
    public double getTotal_Award(String user_name) {
        
        Session session = HibernateUtil.getSession();
        total_award = (double) 0;
        String hql = "select sum(award_amt+continue_amt+offline_amt) from Loan where login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
        	total_award =(Double) li.get(0);
             
        } catch (HibernateException e) {
            e.printStackTrace();
            
        }
        return total_award;
    }

    
    public double getCredit_amt(String year_month,String user_name) {   //得到输入月份的信用卡待还金额
        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        Integer pass_year = Integer.parseInt(year_month.substring(0,4));
        Integer pass_month = Integer.parseInt(year_month.substring(4,6));
        
        String hql = "select sum(amount-paied_amt) from Credit_states where year(return_date) = :pass_year and month(return_date) = :pass_month and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("pass_year",pass_year);
        	query.setParameter("pass_month",pass_month);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return 0;
            }else{
            	if (li.get(0) == null){
            		return 0;
            	}
            }
            tmp_amt =(Double) li.get(0);
        	System.out.println("getCredit_amt ==" + tmp_amt);
             
        } catch (HibernateException e) {
            e.printStackTrace();
            
        }
        return tmp_amt;
	}
/*
	public Double getDueAllAmount(String user_name) {
        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        
        String hql = "select sum(interest_amt+own_amt-fee_amt) from Loan_detail where status=0 and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return (double) 0;
            }else{
            	if (li.get(0) == null){
            		return (double) 0;
            	}
            }
            tmp_amt =(Double) li.get(0);
        	System.out.println("getDueAllAmount ==" + tmp_amt);
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return tmp_amt;
	}
*/
	public void setDueAllAmount(Double dueAllAmount) {
		this.dueAllAmount = dueAllAmount;
	}
	//待收本金
	public Double getDueAllOwn(String user_name) {
        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        
        String hql = "select sum(own_amt) from Loan_detail where status=0 and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return (double) 0;
            }else{
            	if (li.get(0) == null){
            		return (double) 0;
            	}
            }
            tmp_amt =(Double) li.get(0);
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return tmp_amt;
	}
	public void setDueAllOwn(Double dueAllOwn) {
		this.dueAllOwn = dueAllOwn;
	}
	
	//待收利息
	public Double getDueAllIns(String user_name) {
        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        
        String hql = "select sum(interest_amt-fee_amt) from Loan_detail where status=0 and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return (double) 0;
            }else{
            	if (li.get(0) == null){
            		return (double) 0;
            	}
            }
            tmp_amt =(Double) li.get(0);
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return tmp_amt;
	}
	public void setDueAllIns(Double dueAllIns) {
		this.dueAllIns = dueAllIns;
	}
	
	//得到总的年化利率
	public float getDueAllRate(String user_name) {
        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        double dayInsAmt_month =0;    //月标的日利息总和
        double dayAmt_month =0;    //月标的借出总和
        double dayInsAmt_day =0;    //天标的日利息总和
        double dayAmt_day =0;    //天标的借出总和
        //月标的日利率
        String hql = "select sum((interest_amt+award_amt+continue_amt+offline_amt-fee_amt) /duration / 30),sum(amount) from Loan where daily=0 and login_name= :login_name";
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
        	List<Object[]> li =  query.list();
            if (li == null || li.isEmpty()){
            	dayInsAmt_month = (double) 0;
            	dayAmt_month = (double) 0;
            }else{
            	if (li.get(0) == null){
            		dayInsAmt_month = (double) 0;
            		dayAmt_month = (double) 0;
            	}
            }
            for(Object[] return_val: li){
            	if (return_val[0] == null){
            		dayInsAmt_month = (double) 0;
            	}
            	else{
            		dayInsAmt_month = (Double) return_val[0];
            	}
            		
            	if (return_val[1] == null){
            		dayAmt_month = (double) 0;
            	}
            	else{
                	dayAmt_month = (Double) return_val[1];
            	}
            	
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        }

        //天标的日利息
        hql = "select sum((interest_amt+award_amt+continue_amt+offline_amt-fee_amt) /duration),sum(amount) from Loan where daily=1 and login_name= :login_name";
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
        	List<Object[]> li =  query.list();
            if (li == null || li.isEmpty()){
            	dayInsAmt_day = (double) 0;
            	dayAmt_day = (double) 0;
            }else{
            	if (li.get(0) == null){
            		dayInsAmt_day = (double) 0;
            		dayAmt_day = (double) 0;
            	}
            }
            for(Object[] return_val: li){
            	if (return_val[0] == null){
            		dayInsAmt_day = (double) 0;
            	}
            	else{
            		dayInsAmt_month = (Double) return_val[0];
            	}
            		
            	if (return_val[1] == null){
            		dayAmt_day = (double) 0;
            	}
            	else{
            		dayAmt_month = (Double) return_val[1];
            	}
            		
            	
            	
            }
        } catch (HibernateException e) {
            
            e.printStackTrace();
            return 0;
        }
        if ((dayAmt_month + dayAmt_day) > 1){
        	dueAllRate = (float) ((dayInsAmt_month + dayInsAmt_day) / (dayAmt_month + dayAmt_day) *365 * 100);
        }
        else{
        	dueAllRate = 0;
        }
		return dueAllRate;
	}
	public void setDueAllRate(float dueAllRate) {
		this.dueAllRate = dueAllRate;
	}
    
	
	public Double getMonthReturn(String YearMonth,String user_name) {
        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        Integer pass_year=Integer.parseInt(YearMonth.substring(0, 4));
        Integer pass_month=Integer.parseInt(YearMonth.substring(4, 6));
        String hql = "select sum(interest_amt+own_amt-fee_amt) from Loan_detail where status=0 and year(expire_date) = :pass_year and month(expire_date) = :pass_month and login_name = :login_name";
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("pass_year",pass_year);
        	query.setParameter("pass_month",pass_month);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return (double) 0;
            }else{
            	if (li.get(0) == null){
            		return (double) 0;
            	}
            }
            tmp_amt =(Double) li.get(0);
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        return tmp_amt;
	}
	
	
	public List<SummaryPlatform> getSummaryPlatform_list(String user_name) {

        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        List<SummaryPlatform> tmp_summaryPlatform_list= new ArrayList<SummaryPlatform>();
        //平台的总奖励，总本金，总利息
        String hql = "select platform.platform_name,sum(amount),sum(interest_amt),sum(award_amt+continue_amt+continue_amt+offline_amt) "
        		+ " from Loan where "
        		+ " login_name = :login_name group by platform.platform_name";        
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        
        try {
        	Query query = session.createQuery(hql);
        	query.setParameter("login_name",user_name);
            List li = query.list();
            
            if (li == null || li.isEmpty()){
            	return null;
            }else{
            	if (li.get(0) == null){
                	return null;
            	}
            }
            Iterator iter=li.iterator();
            while(iter.hasNext()){
            	Double dueOwnAmt=(double) 0;
            	Double dueIns=(double) 0;
           	 	Object[] obj = (Object[]) iter.next();
            	String platform_name=obj[0].toString();
            	Double allOwnAmt=Double.valueOf(obj[1].toString());
            	Double allInsAmt=Double.valueOf(obj[2].toString());
            	Double allAwardAmt=Double.valueOf(obj[3].toString());
            	
            	//当前平台的待收本金，待收利息
            	String hql2="select sum(dtl.own_amt),sum(dtl.interest_amt-dtl.fee_amt) "
        		+ " from Loan_detail dtl where "
        		+ " dtl.status=0 and dtl.login_name = :login_name and platform.platform_name= :platform_name";
            	Query querydtl =session.createQuery(hql2);
            	querydtl.setParameter("login_name", user_name);
            	querydtl.setParameter("platform_name", platform_name);
            	List lidtList =querydtl.list();
                if (lidtList == null || lidtList.isEmpty()){
                }else{
                	if (li.get(0) == null){
                		dueOwnAmt=(double) 0;
                		dueIns=(double) 0;
                	}
                	else{
                		//System.out.println("CCCCCC == " + lidtList.get(0).toString());
                		Object[] objdtl = (Object[]) lidtList.get(0);
                		dueOwnAmt=(Double) objdtl[0];
                		dueIns=(Double) objdtl[1];
                	}
                }
        		SummaryPlatform summaryPlatform = new SummaryPlatform();
                summaryPlatform.setPlatform_name(platform_name);
                summaryPlatform.setDueAllAmt(dueOwnAmt+dueIns);
                summaryPlatform.setDueIns(dueIns);
                summaryPlatform.setDueOwnAmt(dueOwnAmt);
                summaryPlatform.setInAward(allAwardAmt);
                summaryPlatform.setInIns(allInsAmt-dueIns);
                summaryPlatform.setInTotal(allInsAmt+allAwardAmt);
                
                tmp_summaryPlatform_list.add(summaryPlatform);
                     //tmp_summaryPlatform_list.add(summaryPlatxxxxxform);
            }
            	
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        summaryPlatform_list=tmp_summaryPlatform_list;
		return summaryPlatform_list;
	}
	
	public List<SummaryMonth> getSummaryMonth_list(String user_name) {

        Session session = HibernateUtil.getSession();
        double tmp_amt = (double) 0;
        List<SummaryMonth> tmp_summaryMonth_list= new ArrayList<SummaryMonth>();
        //平台的总奖励，总本金，总利息
        String hql = "select sum(amount),sum(interest_amt),sum(award_amt+continue_amt+continue_amt+offline_amt) "
        		+ " from Loan where "
        		+ " login_name = :login_name and date_format(loan_date,'%Y%m') = :monthVal";        
        //session = HibernateUtil.getSession();
        //session.beginTransaction();
        Date curr_date;        
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
    	Calendar   cal=Calendar.getInstance();
    	curr_date = cal.getTime();
    	String curr_month = df.format(cal.getTime());
        
        try {
        	for(int i=0;i<18;i++){
        		if (i>0){
        			cal.add(Calendar.MONTH,-1);
        		}
            	String monthVal = df.format(cal.getTime());
            	
            	Query query = session.createQuery(hql);
            	query.setParameter("login_name",user_name);
            	query.setParameter("monthVal",monthVal);
                List li = query.list();
                Double ownAmt=(double) 0;
                Double awardAmt=(double) 0;
                
                if (li == null || li.isEmpty()){
                	return null;
                }else{
                	if (li.get(0) == null){
                    	return null;
                	}
                	else{
                		Object[] obj = (Object[]) li.get(0);
                		if (obj[0] != null){
                			ownAmt=Double.valueOf(obj[0].toString());
                		}
                    	
                    	//Double insAmt=Double.valueOf(obj[2].toString());
                		if (obj[2] != null){
                			awardAmt=Double.valueOf(obj[2].toString());
                		}
                	}
                }
            	Double insAmt=(double) 0;
            	//当前平台的待收本金，待收利息
            	String hql2="select sum(dtl.own_amt),sum(dtl.interest_amt-dtl.fee_amt) "
        		+ " from Loan_detail dtl where "
        		+ " dtl.status=1 and dtl.login_name = :login_name and date_format(expire_date,'%Y%m') = :monthVal";
            	Query querydtl =session.createQuery(hql2);
            	querydtl.setParameter("login_name", user_name);
            	querydtl.setParameter("monthVal", monthVal);
            	List lidtList =querydtl.list();
                if (lidtList == null || lidtList.isEmpty()){
                }else{
                	if (li.get(0) == null){
                		insAmt=(double) 0;
                	}
                	else{
                		//System.out.println("CCCCCC == " + lidtList.get(0).toString());
                		Object[] objdtl = (Object[]) lidtList.get(0);
                		if (objdtl[1]!=null){
                			insAmt=(Double) objdtl[1];
                		}
                		
                	}
                }
        		SummaryMonth summaryMonth = new SummaryMonth();
        		summaryMonth.setAwardAmt(awardAmt);
        		summaryMonth.setInsAmt(insAmt);
        		summaryMonth.setMonthVal(monthVal.substring(0,4)+"年"+monthVal.substring(4,6)+"月");
        		summaryMonth.setOwnAmt(ownAmt);
                tmp_summaryMonth_list.add(summaryMonth);
                     //tmp_summaryPlatform_list.add(summaryPlatxxxxxform);
        	}
            	
             
        } catch (HibernateException e) {
            
            e.printStackTrace();
        }
        summaryMonth_list=tmp_summaryMonth_list;
		return summaryMonth_list;
	}	
	public void setSummaryPlatform_list(List<SummaryPlatform> summaryPlatform_list) {
		this.summaryPlatform_list = summaryPlatform_list;
	}
}