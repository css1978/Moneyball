package com.loanly.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loanly.model.Loan_detail;
import com.loanly.util.HibernateUtil;


public class LoanDtlDaoImpl extends HibernateDaoSupport implements LoanDtlDao {
    /**
     * 分页查询
     * @param hql 查询的条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return
     */
	
	private Loan_detailManager loan_detailManager;
	
	public List<Loan_detail> queryForPage(String user_name,String platform_id,String status,Date loanStartDate,Date loanEndDate,final int offset,final int length){
    	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        Session session = HibernateUtil.getSession();
    	String hql = "from Loan_detail where login_name = :login_name";
        if (loanStartDate != null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d') >= :loanStartDate";
        }
        if  (loanEndDate!=null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d') <= :loanEndDate";
        }
        if (platform_id!=null && !platform_id.trim().isEmpty()){
        	hql += " and platform_id in (" + platform_id +")";
        }
        if(status!=null && !status.trim().isEmpty()){
        	hql += " and status in (" + status +")";
        }
        
        /*
    	if (return_status==0){
    		//未回款
    		hql += " and status=0";
    	}
    	else if(return_status==1){
    		//已回款
    		hql += " and status=1";
    	}
    	else{
    		//所有状态
    		hql += " and status in (0,1)";
    	}
    	*/
        hql += " order by expire_date desc";
    	Query query = session.createQuery(hql);
    	query.setParameter("login_name",user_name);
        if (loanStartDate != null){
        	query.setParameter("loanStartDate",f.format(loanStartDate).toString());
        }
        if  (loanEndDate!=null){
        	query.setParameter("loanEndDate",f.format(loanEndDate).toString());
        }
        query.setFirstResult(offset);
        query.setMaxResults(length);
        List list = query.list();
        return list;

    }
    
    
    /**
     * 查询所有记录数
     * @return 总记录数
     */
    public int getAllRowCount(String user_name,Date loanStartDate,Date loanEndDate,String platform_id,String status){
    	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        Session session = HibernateUtil.getSession();
    	String hql = "select count(record_id) from Loan_detail where login_name = :login_name";
        if (loanStartDate != null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d') >= :loanStartDate";
        }
        if  (loanEndDate!=null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d') <= :loanEndDate";
        }
        if (platform_id!=null && !platform_id.trim().isEmpty()){
        	hql += " and platform_id in (" + platform_id +")";
        }
        if(status!=null && !status.trim().isEmpty()){
        	hql += " and status in (" + status +")";
        }
        /*
    	if (return_status==0){
    		//未回款
    		hql += " and status=0";
    	}
    	else if(return_status==1){
    		//已回款
    		hql += " and status=1";
    	}
    	else{
    		//所有状态
    		hql += " and status in (0,1)";
    	}
    	*/
    		
        
    	Query query = session.createQuery(hql);
    	query.setParameter("login_name",user_name);
        if (loanStartDate != null){
        	query.setParameter("loanStartDate",f.format(loanStartDate).toString());
        }
        if  (loanEndDate!=null){
        	query.setParameter("loanEndDate",f.format(loanEndDate).toString());
        }
    	List li = query.list();
        if (li == null || li.isEmpty()){
        	return 0;
        }else{
        	if (li.get(0) == null){
        		return 0;
        	}
        }
        int rt = Integer.valueOf(li.get(0).toString()); 
        return rt;
    }

}
