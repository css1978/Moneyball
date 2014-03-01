package com.loanly.view;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.loanly.controller.PlatformManager;
import com.loanly.model.Platform;
import com.loanly.util.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


public class PageListLoanAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -605090849136084014L;
	/**
	 * 
	 */
    private Date loanStartDate;
    private Date loanEndDate;
    private String status;
    private List<Platform> platformList;
    private String platform_id;
//    private String platform_id_multiple;
    private PlatformManager platformManager;

	//通过applicationContext.xml配置文件注入memberService的值
    private LoanService loanService;
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }
    
    private int page;    //第几页
    
    private PageBean pageBean;    //包含分布信息的bean
    
/*
    public void prepare() throws Exception {
        //初始化平台数据的下拉列表
    	try{
            platformManager = new PlatformManager();
            this.platformList = platformManager.list();
            ServletActionContext.getRequest().setAttribute("platformList",platformList);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		addActionError("登录超时，请重新登录！");
    		
    	}
    	
    }
*/
    public String execute() throws Exception {
        //分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
//    	System.out.println("tmp_expireStartDate == " + tmp_expireStartDate.toString());
//    	System.out.println("platform_id == " + platform_id);
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
    	
        this.pageBean = loanService.queryForPage(20, page,loanStartDate,loanEndDate,platform_id,status);
        if (page == 0){
        	this.pageBean.setCurrentPage(1);
        }
        return SUCCESS;
    }
    

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
	public List<Platform> getPlatformList() {
		return platformList;
	}
	public void setPlatformList(List<Platform> platformList) {
		this.platformList = platformList;
	}
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

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}
/*
	public String getPlatform_id_multiple() {
		return platform_id_multiple;
	}

	public void setPlatform_id_multiple(String platform_id_multiple) {
		this.platform_id_multiple = platform_id_multiple;
	}
*/
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
