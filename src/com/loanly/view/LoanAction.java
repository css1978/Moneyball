package com.loanly.view;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
 

import com.loanly.controller.LoanManager;
import com.loanly.controller.PlatformManager;
import com.loanly.model.Loan;
import com.loanly.model.Platform;
 
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
 
 
public class LoanAction extends ActionSupport implements Preparable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2325759296649094711L;
	private Loan loan;
    private List<Loan> loanList;
    private Long loansid;
    private Long platform_id;
    private int platform_index;
    private Platform platform;
    private List<Platform> platformList;
    private Date loanStartDate;
    private Date loanEndDate;
    
//    private Double sumAmount;
 
    private LoanManager loanManager;
    private PlatformManager platformManager;
    
    private String result;
 
    public LoanAction() {
    	loanManager = new LoanManager();
    	platformManager = new PlatformManager();
    }
    public void prepare() throws Exception {
        //初始化平台数据的下拉列表
    	try{
            this.platformList = platformManager.list();
            ServletActionContext.getRequest().setAttribute("platformList",platformList);
    	}
    	catch(Exception ex){
    		addActionError("登录超时，请重新登录！");
    	}
    }
    
    @SkipValidation
    public String execute() {
        //System.out.println("LoanAction execute called");
        return SUCCESS;
    }
    
    

    @SkipValidation
    public String getPlatformFee() {
    	/*
    	Float fee_rate = this.platformList.get(platform_index).getPlatform_rate();
    	setResult(fee_rate.toString());
    	*/
    	Long platform_idx = getPlatform_id();  //实际是拿到platform list的index
//    	System.out.println("getPlatformFee called " + platform_idx);
    	
    	setResult(String.valueOf(platformList.get(platform_idx.intValue()).getPlatform_rate()));
    	return "getPlatformFee";
    }

    @SkipValidation
    public String getrecord() {
    	if (this.loansid != null){
    		this.loan = loanManager.queryByID(this.loansid);
    	}
        return SUCCESS;
    }

    
    public String update() {
    	//Loan loan = loanManager.queryByID(getLoan_id());
        try {
        	Long loanid = getLoansid();
//        	System.out.println("loanid11 == " + loanid);
        	loanManager.delete(loanid);
        	Loan Loan = getLoan();
        	Long platform_id = getPlatform_id();
//        	System.out.println("platform_id11 == " + platform_id);
        	Platform platform = platformManager.queryByID(platform_id);
        	loan.setPlatform(platform);
        	loanManager.add(loan);
            addActionMessage("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，修改失败！");
        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String getList() {
    	this.loanList = null;
        this.loanList = loanManager.list(loanStartDate,loanEndDate);
        //System.out.println("getList == " + this.loanList.get(0).getPlatform().getPlatform_name());
        //this.setSumAmount(loanManager.getSumAmount());
        //System.out.println("LoanAction getList called");
        return SUCCESS;
    }
    
 
    public String add() {
//        System.out.println(getLoan().getActive());
//        System.out.println(getLoan().getFirstname());
        try {
        	Loan loan = getLoan();
        	loan.setPlatform(platformManager.queryByID(getPlatform_id()));
        	
        	loanManager.add(loan);
            addActionMessage("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，添加失败！");
        }
        //this.loanList = loanManager.list(null,null);
        return SUCCESS;
    }
 
    @SkipValidation
    public String delete() {
        try {
        	loanManager.delete(getLoansid());
        	addActionMessage("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("系统错误，删除失败！");
        }
    	
        return SUCCESS;
    }
 
    public Loan getLoan() {
    	//System.out.println("loan��firstname == " + loan.getFirstName());
        return loan;
    }
 
    public List<Loan> getLoanList() {
//    	System.out.println("getLoanList == " + loanList.get(0).getPlatform().getPlatform_name());
        return loanList;
    }
 

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
 
    public void setLoanList(List<Loan> loansList) {
    	//System.out.println("setLoanList == " + loanList.get(0).getPlatform().getPlatform_name());
        this.loanList = loansList;
    }
 
    public Long getLoansid() {
        return loansid;
    }
 
    public void setLoansid(Long loansid) {
        this.loansid = loansid;
    }

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public List<Platform> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<Platform> platformList) {
		this.platformList = platformList;
	}
	public Long getPlatform_id() {
		return platform_id;
	}
	public void setPlatform_id(Long platform_id) {
		this.platform_id = platform_id;
	}
	public int getPlatform_index() {
		return platform_index;
	}
	public void setPlatform_index(int platform_index) {
		this.platform_index = platform_index;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getLoanEndDate() {
		return loanEndDate;
	}
	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}
	public Date getLoanStartDate() {
		return loanStartDate;
	}
	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	
	
}