package com.loanly.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.loanly.controller.Loan_detailManager;
import com.loanly.model.Loan_detail;
 
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
 
public class Loan_detailAction extends ActionSupport {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2882852657539487965L;
	/**
	 * 
	 */
	private Loan_detail loan_detail;
    private List<Loan_detail> loan_detail_list;
    private Long Loan_detail_id;
 
    private Loan_detailManager loan_detailManager;
    private Date expireStartDate;
    private Date expireEndDate;
    
 
    public Loan_detailAction() {
    	loan_detailManager = new Loan_detailManager();
    }
 
    @SkipValidation
    public String execute() {
        return SUCCESS;
    }
 
    public String add() {
//        System.out.println(getPlatform().getFirstname());
        try {
        	loan_detailManager.add(getLoan_detail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
 
    @SkipValidation
    public String delete() {
    	loan_detailManager.delete(getLoan_detail_id());
        return SUCCESS;
    }
 
    public String setLoanDtlReturn() {
    	getLoan_detail_id();
    	Date current_time = new Date();
    	loan_detail = loan_detailManager.getDetailByID(Loan_detail_id);
    	loan_detail.setStatus(1);			//1 = 回款
    	loan_detail.setReturn_date(current_time);
    	loan_detailManager.update(loan_detail);
    	addActionMessage("回款成功.");
        return SUCCESS;
    }
    public String setLoanDtlNotReturn() {
    	getLoan_detail_id();
    	loan_detail = loan_detailManager.getDetailByID(Loan_detail_id);
    	loan_detail.setStatus(0);			//0 = 回款
    	loan_detailManager.update(loan_detail);
    	addActionMessage("修改成功.");
        return SUCCESS;
    }
    
    public String setLoanDtlBad() {
    	getLoan_detail_id();
    	loan_detail = loan_detailManager.getDetailByID(Loan_detail_id);
    	loan_detail.setStatus(2);			//2 = 坏账
    	loan_detailManager.update(loan_detail);
    	addActionMessage("修改成功.");
        return SUCCESS;
    }

    public String getList() {
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	String user_name = attibutes.get("username").toString();
        this.loan_detail_list = loan_detailManager.list(user_name,expireStartDate,expireEndDate);
        //System.out.println("getList == " + this.loanList.get(0).getPlatform().getPlatform_name());
        //this.setSumAmount(loanManager.getSumAmount());
        System.out.println("Loan_detailAction getList called");
        return SUCCESS;
    }
    
    public void setId(Long Loan_detail_id) {
        this.setLoan_detail_id(Loan_detail_id);
    }

	public Loan_detail getLoan_detail() {
		return loan_detail;
	}

	public void setLoan_detail(Loan_detail loan_detail) {
		this.loan_detail = loan_detail;
	}

	public List<Loan_detail> getLoan_detail_list() {
		return loan_detail_list;
	}

	public void setLoan_detail_list(List<Loan_detail> loan_detail_list) {
		this.loan_detail_list = loan_detail_list;
	}

	public Long getLoan_detail_id() {
		return Loan_detail_id;
	}

	public void setLoan_detail_id(Long loan_detail_id) {
		Loan_detail_id = loan_detail_id;
	}

	public Date getExpireStartDate() {
		return expireStartDate;
	}

	public void setExpireStartDate(Date expireStartDate) {
		this.expireStartDate = expireStartDate;
	}

	public Date getExpireEndDate() {
		return expireEndDate;
	}

	public void setExpireEndDate(Date expireEndDate) {
		this.expireEndDate = expireEndDate;
	}
}
