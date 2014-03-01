package com.loanly.view;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.loanly.controller.PlatformManager;
import com.loanly.model.Platform;
import com.loanly.util.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


public class PageListLoanDtlAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -584689383428886801L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	
    private Date expireStartDate;
    private Date expireEndDate;
    private String status;
    private List<Platform> platformList;
    private String platform_id;

    private PlatformManager platformManager;
	//通过applicationContext.xml配置文件注入memberService的值
    private LoanDtlService loanDtlService;
    public void setLoanDtlService(LoanDtlService loanDtlService) {
        this.loanDtlService = loanDtlService;
    }
/*
    public void prepare() throws Exception {
        //初始化平台数据的下拉列表
    	try{
    	}
    	
    }
*/    
    
    private int page;    //第几页
    
    private PageBean pageBean;    //包含分布信息的bean
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {        //若URL中无此参数,会默认为第1页
        this.page = page;
    }

    public Date getExpireStartDate() {
        return expireStartDate;
    }

    public void setExpireStartDate(Date expireStartDate) {        //若URL中无此参数,会默认为第1页
        this.expireStartDate = expireStartDate;
    }

    public Date getExpireEndDate() {
        return expireEndDate;
    }

    public void setExpireEndDate(Date expireEndDate) {        //若URL中无此参数,会默认为第1页
        this.expireEndDate = expireEndDate;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
//
    public String execute() throws Exception {
    	try{
            platformManager = new PlatformManager();
            this.platformList = platformManager.list();
            ServletActionContext.getRequest().setAttribute("platformList",platformList);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		addActionError("登录超时，请重新登录！");
    		return INPUT;
    	}
    	
        //分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
    	
    	Date tmp_expireStartDate = getExpireStartDate();
    	Date tmp_expireEndDate = getExpireEndDate();
 //   	System.out.println("tmp_expireStartDate == " + tmp_expireStartDate.toString());
//    	System.out.println("platform_id == " + platform_id);
        this.pageBean = loanDtlService.queryForPage(20, page,tmp_expireStartDate,tmp_expireEndDate,platform_id,status);
        if (page == 0){
        	this.pageBean.setCurrentPage(1);
        }
//        System.out.println("pageBean == " + this.pageBean.toString() );
        return SUCCESS;
    }

//代收明细查询
    public String dueList() throws Exception {
        //分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
    	Date tmp_expireStartDate = getExpireStartDate();
    	Date tmp_expireEndDate = new Date(System.currentTimeMillis());
    	status = "0";  //待收状态的待收明细
    	System.out.println("tmp_expireStartDate == " + tmp_expireStartDate.toString());
    	System.out.println("platform_id == " + platform_id);
        this.pageBean = loanDtlService.queryForPage(10, page,tmp_expireStartDate,tmp_expireEndDate,platform_id,status);
        if (page == 0){
        	this.pageBean.setCurrentPage(1);
        }
        System.out.println("pageBean == " + this.pageBean.toString() );
        return SUCCESS;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}

	public List<Platform> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<Platform> platformList) {
		this.platformList = platformList;
	}
    
}
