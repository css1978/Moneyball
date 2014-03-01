package com.loanly.view;



import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.loanly.controller.LoanDtlDao;
import com.loanly.util.PageBean;
import com.loanly.model.Loan_detail;
import com.opensymphony.xwork2.ActionContext;

public class LoanDtlServiceImpl implements LoanDtlService{
	//通过applicationContext.xml配置文件注入LoanDtlDao的值
    private LoanDtlDao loanDtlDao;
    public void setLoanDtlDao(LoanDtlDao loanDtlDao) {
        this.loanDtlDao = loanDtlDao;
    }
    
    /**
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    public PageBean queryForPage(int pageSize,int page,Date loanStartDate,Date loanEndDate,
			String platform_id, String status){
    	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
/*    	String hql = "from Loan_detail where login_name ='" + user_name +"'";
        if (loanStartDate != null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d')>=" + f.format(loanStartDate);
        }
        if  (loanEndDate!=null){
        	hql += " and DATE_FORMAT(expire_date,'%Y%m%d')<=" + f.format(loanEndDate);
        }
        String cntSql = "select count(record_id) " + hql;
*/
    	if (pageSize == 0){
    		pageSize = 20;
    	}
    	int allRow = loanDtlDao.getAllRowCount(user_name,loanStartDate,loanEndDate,platform_id,status);
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        //hql += " order by expire_date desc";
        List<Loan_detail> list = loanDtlDao.queryForPage(user_name,platform_id,status,loanStartDate,loanEndDate,offset, length);        //"一页"的记录
        //    //总记录数
        
        //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
    }

}
