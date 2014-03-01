package com.loanly.view;

import java.sql.Date;

import com.loanly.util.PageBean;
import com.opensymphony.xwork2.ActionSupport;


public class PageListPlatformAction extends ActionSupport{

	/**
	 * 
	 */
	/**
	 * 
	 */

	//通过applicationContext.xml配置文件注入memberService的值
    private PlatformService platformService;
    public void setPlatformService(PlatformService platformService) {
        this.platformService = platformService;
    }
    
    private int page;    //第几页
    
    private PageBean pageBean;    //包含分布信息的bean
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {        //若URL中无此参数,会默认为第1页
        this.page = page;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public String execute() throws Exception {
        //分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
        this.pageBean = platformService.queryForPage(7, page);
        if (page == 0){
        	this.pageBean.setCurrentPage(1);
        }
        return SUCCESS;
    }


}
