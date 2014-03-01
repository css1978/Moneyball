package com.loanly.util;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LazyFilter implements Filter {

	public void destroy() {

	}


	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.getTransaction();
			transaction.begin();
			//transaction = HibernateUtil.getSession().beginTransaction();
			chain.doFilter(request, response);
			if (!transaction.wasCommitted()){
				transaction.commit();
			}
		} catch (Exception e) {
			if (!transaction.wasRolledBack()){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
