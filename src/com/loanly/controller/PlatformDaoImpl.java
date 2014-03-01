package com.loanly.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loanly.model.Platform;
import com.loanly.controller.PlatformDao;
import com.loanly.util.HibernateUtil;


public class PlatformDaoImpl extends HibernateDaoSupport implements PlatformDao {
    /**
     * 分页查询
     * @param hql 查询的条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return
     */
	
    public List<Platform> queryForPage(String user_name,final int offset,final int length){
        Session session = HibernateUtil.getSession();
    	String hql = "from Platform where login_name = :login_name";
    	Query query = session.createQuery(hql);
    	query.setParameter("login_name",user_name);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        List list = query.list();
        return list;

    }
    
    
    /**
     * 查询所有记录数
     * @return 总记录数
     */
    public int getAllRowCount(String user_name){
        Session session = HibernateUtil.getSession();
    	String hql = "select count(platform_id) from Platform where login_name = :login_name";
        
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
        int rt = Integer.valueOf(li.get(0).toString()); 
        return rt;
    }

}
