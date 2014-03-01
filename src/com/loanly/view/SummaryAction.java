package com.loanly.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.loanly.controller.Loan_detailManager;
import com.loanly.controller.SummaryManager;
import com.loanly.model.Loan_detail;
import com.loanly.model.Platform;
import com.loanly.model.SummaryPlatform;
import com.loanly.model.SummaryMonth;
import com.loanly.util.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
 
public class SummaryAction extends ActionSupport {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2325759296649094711L;
    private String user_name;
    private SummaryManager summaryManager;
    private Loan_detailManager loan_detailManager;
    private Double sumAmount;			//借出总金额
    private Double assetAmount;			//资产总金额
    private Double debitAmount;			//负债总金额
    private Double netAmount;			//净资产总金额
//    private Double curr_month_in;		//当月收益
    private Double curr_month_interest; //当月利息 
    private Double curr_month_award;	//当月奖励
//    private Double total_in;			//总收入
    private Double total_interest;		//总利息
    private Double total_award;			//总奖励
    
//    private Double dueAllAmount;		//总待收金额
    private Double dueAllOwn;			//总待收本金
    private Double dueAllIns;			//总待收利息-手续费
    private float dueAllRate;			//总利率

	private String next1_month;
	private String next2_month;
	private String next3_month;
	private String next4_month;
	private String next5_month;
	private String next6_month;
	private String curr_month;
	private Date curr_date;
    
	private Double curr_month_amt;
	private Double next1_month_amt;
	private Double next2_month_amt;
	private Double next3_month_amt;
	private Double next4_month_amt;
	private Double next5_month_amt;
	private Double next6_month_amt;
	
	private List<SummaryPlatform> summaryPlatform_list;
	private List<SummaryMonth> summaryMonth_list;
	
    public SummaryAction() {
    	summaryManager = new SummaryManager();
    	loan_detailManager = new Loan_detailManager();
    	setSumAmount(0d);
    	setAssetAmount(0d);
    	setDebitAmount(0d);
    	setNetAmount(0d);
    	setCurr_month_interest(0d);
    	setCurr_month_award(0d);
    	setTotal_interest(0d);
    	setTotal_award(0d);

    	SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
    	Calendar   cal=Calendar.getInstance();
    	curr_date = cal.getTime();
    	curr_month = df.format(cal.getTime());
    	cal.add(Calendar.MONTH,1);
    	next1_month = df.format(cal.getTime());
    	cal.add(Calendar.MONTH,1);
    	next2_month = df.format(cal.getTime());
    	cal.add(Calendar.MONTH,1);
    	next3_month = df.format(cal.getTime());
    	cal.add(Calendar.MONTH,1);
    	setNext4_month(df.format(cal.getTime()));
    	cal.add(Calendar.MONTH,1);
    	setNext5_month(df.format(cal.getTime()));
    	cal.add(Calendar.MONTH,1);
    	setNext6_month(df.format(cal.getTime()));
    	
    }
 
    public String execute() {
    	Map attibutes = (Map) ActionContext.getContext().getSession(); 
    	user_name = attibutes.get("username").toString();
    	
    	setSumAmount (summaryManager.getSumAmount(user_name));
        //
    	setAssetAmount(summaryManager.getAssetAmount(user_name));
    	setDebitAmount(summaryManager.getDebitAmount(user_name));
        //curr_month_interest = 当月利息
    	setCurr_month_interest(summaryManager.getCurr_Month_Interest(user_name));
        
        //curr_month_award = 当月奖励
    	setCurr_month_award(summaryManager.getCurr_Month_Award(user_name));
    	
        //total_interest = 总利息
    	setTotal_interest(summaryManager.getTotal_Interest(user_name));
        
        //total_award = 总奖励
    	//setCurr_month_in(summaryManager.getCurr_Month_Interest() + summaryManager.getCurr_Month_Award());
    	double totalAward = summaryManager.getTotal_Award(user_name);
    	setTotal_award(totalAward);

        //netamount = 净资产
        setNetAmount(this.getAssetAmount() - this.getDebitAmount());
        
        //setDueAllAmount(summaryManager.getDueAllAmount(user_name));
        setDueAllOwn(summaryManager.getDueAllOwn(user_name));
        setDueAllIns(summaryManager.getDueAllIns(user_name));

        setCurr_month_amt(summaryManager.getMonthReturn(curr_month,user_name));
        setNext1_month_amt(summaryManager.getMonthReturn(next1_month,user_name));
        setNext2_month_amt(summaryManager.getMonthReturn(next2_month,user_name));
        setNext3_month_amt(summaryManager.getMonthReturn(next3_month,user_name));
        setNext4_month_amt(summaryManager.getMonthReturn(next4_month,user_name));
        setNext5_month_amt(summaryManager.getMonthReturn(next5_month,user_name));
//		float rate=0;
//		rate = (float) ((this.total_interest + this.total_award+this.dueAllIns)/this.sumAmount * 100);
        setDueAllRate(summaryManager.getDueAllRate(user_name));
        setSummaryPlatform_list(summaryManager.getSummaryPlatform_list(user_name));
        setSummaryMonth_list(summaryManager.getSummaryMonth_list(user_name));
        //setNext6_month_amt(summaryManager.getMonthReturn(next6_month));
        //float yearRate
        //setDueAllRate(getDueAllIns()/getDueAllOwn());
        return SUCCESS;
    }
/*    
    public String formatDouble(double s){
        DecimalFormat fmt = new DecimalFormat("\u00A4##0.00");
        return fmt.format(s);
*/
	public Double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}


	public Double getCurr_month_interest() {
		return curr_month_interest;
	}

	public void setCurr_month_interest(Double curr_month_interest) {
		this.curr_month_interest = curr_month_interest;
	}

	public Double getCurr_month_award() {
		return curr_month_award;
	}

	public void setCurr_month_award(Double curr_month_award) {
		this.curr_month_award = curr_month_award;
	}

	public Double getTotal_interest() {
		return total_interest;
	}

	public void setTotal_interest(Double total_interest) {
		this.total_interest = total_interest;
	}

	public Double getTotal_award() {
		return total_award;
	}

	public void setTotal_award(Double total_award) {
		this.total_award = total_award;
	}


	public Double getAssetAmount() {
		return assetAmount;
	}

	public void setAssetAmount(Double assetAmount) {
		this.assetAmount = assetAmount;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getCurr_month() {
		return curr_month;
	}

	public void setCurr_month(String next1_month) {
		this.curr_month = curr_month;
	}

	public String getNext1_month() {
		return next1_month;
	}

	public void setNext1_month(String next1_month) {
		this.next1_month = next1_month;
	}

	public String getNext2_month() {
		return next2_month;
	}

	public void setNext2_month(String next2_month) {
		this.next2_month = next2_month;
	}

	public String getNext3_month() {
		return next3_month;
	}

	public void setNext3_month(String next3_month) {
		this.next3_month = next3_month;
	}

	public Double getNext1_month_amt() {
		return next1_month_amt;
	}

	public void setNext1_month_amt(Double next1_month_amt) {
		this.next1_month_amt = next1_month_amt;
	}

	public Double getNext3_month_amt() {
		return next3_month_amt;
	}

	public void setNext3_month_amt(Double next3_month_amt) {
		this.next3_month_amt = next3_month_amt;
	}

	public Double getNext2_month_amt() {
		return next2_month_amt;
	}

	public void setNext2_month_amt(Double next2_month_amt) {
		this.next2_month_amt = next2_month_amt;
	}

	public String getNext4_month() {
		return next4_month;
	}

	public void setNext4_month(String next4_month) {
		this.next4_month = next4_month;
	}

	public String getNext5_month() {
		return next5_month;
	}

	public void setNext5_month(String next5_month) {
		this.next5_month = next5_month;
	}

	public String getNext6_month() {
		return next6_month;
	}

	public void setNext6_month(String next6_month) {
		this.next6_month = next6_month;
	}

	public Double getNext4_month_amt() {
		return next4_month_amt;
	}

	public void setNext4_month_amt(Double next4_month_amt) {
		this.next4_month_amt = next4_month_amt;
	}

	public Double getNext5_month_amt() {
		return next5_month_amt;
	}

	public void setNext5_month_amt(Double next5_month_amt) {
		this.next5_month_amt = next5_month_amt;
	}

	public Double getNext6_month_amt() {
		return next6_month_amt;
	}

	public void setNext6_month_amt(Double next6_month_amt) {
		this.next6_month_amt = next6_month_amt;
	}

	public Double getDueAllOwn() {
		return dueAllOwn;
	}

	public void setDueAllOwn(Double dueAllOwn) {
		this.dueAllOwn = dueAllOwn;
	}

	public Double getDueAllIns() {
		return dueAllIns;
	}

	public void setDueAllIns(Double dueAllIns) {
		this.dueAllIns = dueAllIns;
	}

	public float getDueAllRate() {
		return dueAllRate;
	}

	public void setDueAllRate(float dueAllRate) {
		this.dueAllRate = dueAllRate;
	}

	public Double getCurr_month_amt() {
		return curr_month_amt;
	}

	public void setCurr_month_amt(Double curr_month_amt) {
		this.curr_month_amt = curr_month_amt;
	}

	public List<SummaryPlatform> getSummaryPlatform_list() {
		return summaryPlatform_list;
	}

	public void setSummaryPlatform_list(List<SummaryPlatform> summaryPlatform_list) {
		this.summaryPlatform_list = summaryPlatform_list;
	}

	public List<SummaryMonth> getSummaryMonth_list() {
		return summaryMonth_list;
	}

	public void setSummaryMonth_list(List<SummaryMonth> summaryMonth_list) {
		this.summaryMonth_list = summaryMonth_list;
	}
}