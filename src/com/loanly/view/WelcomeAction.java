package com.loanly.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.loanly.controller.Loan_detailManager;
import com.loanly.model.Loan_detail;
import com.loanly.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
 
public class WelcomeAction extends ActionSupport {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1756699498197526613L;
	/**
	 * 
	 */
    private String user_name;
    private Loan_detailManager loan_detailManager;
	private java.util.Date curr_date;

	private List<Loan_detail> returnTodayList;
    private int page;    //第几页
    
    private PageBean pageBean;    //包含分布信息的bean
    private LoanDtlService loanDtlService;
    public void setLoanDtlService(LoanDtlService loanDtlService) {
        this.loanDtlService = loanDtlService;
    }    
    public WelcomeAction() {
    	loan_detailManager = new Loan_detailManager();
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
    	Calendar   cal=Calendar.getInstance();
    	curr_date = cal.getTime();
    }
 
    public String execute() {
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	user_name = attibutes.get("username").toString();
        //当日应回款
    	List<Loan_detail> returnTodayList = loan_detailManager.getReturnList(user_name, curr_date);
    	if (returnTodayList!=null)
    		setReturnTodayList(returnTodayList);
//        System.out.println("SummaryAction execute called");
        return SUCCESS;
    }

  //代收明细查询
    public String todayReturnList() throws Exception {
        //分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
    	Date tmp_expireStartDate = null;
    	Date tmp_expireEndDate = new Date(System.currentTimeMillis());
    	String platform_id=null;
    	String status = "0";  //待收状态的待收明细
        this.pageBean = loanDtlService.queryForPage(10, page,tmp_expireStartDate,tmp_expireEndDate,platform_id,status);
        if (page == 0){
        	this.pageBean.setCurrentPage(1);
        }
        return SUCCESS;
    }
    
    /*    
    public String formatDouble(double s){
        DecimalFormat fmt = new DecimalFormat("\u00A4##0.00");
        return fmt.format(s);
*/

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<Loan_detail> getReturnTodayList() {
		return returnTodayList;
	}

	public void setReturnTodayList(List<Loan_detail> returnTodayList) {
		this.returnTodayList = returnTodayList;
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


}