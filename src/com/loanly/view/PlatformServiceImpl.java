package com.loanly.view;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.loanly.controller.PlatformDao;
import com.loanly.util.PageBean;
import com.loanly.model.Platform;
import com.opensymphony.xwork2.ActionContext;

public class PlatformServiceImpl implements PlatformService{
	//通过applicationContext.xml配置文件注入LoanDtlDao的值
    private PlatformDao platformDao;
    public void setPlatformDao(PlatformDao platformDao) {
        this.platformDao = platformDao;
    }
    
    /**
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    public PageBean queryForPage(int pageSize,int page){
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
    	int allRow = platformDao.getAllRowCount(user_name);
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        //hql += " order by expire_date desc";
        List<Platform> list = platformDao.queryForPage(user_name,offset, length);        //"一页"的记录
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
