package com.loanly.util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.loanly.model.Loan;
import com.loanly.model.Loan_detail;

public class SplitLoanDtl {
	public List<Loan_detail> proceed(Loan loan){
		List<Loan_detail> loan_details = new ArrayList<Loan_detail>();
		Integer daily=loan.getDaily();
		Integer return_type=loan.getReturn_type();

    	Calendar   cal=Calendar.getInstance();
    	//Date current_time = new Date();
    	cal.setTime(loan.getLoan_date());
    	float ins_rate=0;
    	
		float monthactive=0;  //月利率
		double totalmoney=0;  //总金额
		Integer totalmonth;   //期限
		
		double fee_amt=0;
		double ins_amt=0;
    	
		if (loan.getInterest_rate_type().intValue() == 0)  //年利率
		{
			ins_rate = loan.getInterest_rate();
		}
		else if (loan.getInterest_rate_type().intValue() == 1)  //月利率
		{
			ins_rate = loan.getInterest_rate() * 12 ;
		}
		else													//日利率
		{
			ins_rate = loan.getInterest_rate() * 365 ;
		}
    	
		if (daily.intValue() == 0)  //月标
		{
			switch(return_type.intValue()){
			case 0:   //等额本金
				double divAmt=0;
				double remainAmt=0;
				divAmt = loan.getAmount()/ loan.getDuration();
				remainAmt = loan.getAmount();
				for(int i=0;i<loan.getDuration();i++)
				{
					Loan_detail loan_detail=new Loan_detail();
					loan_detail.setLoan(loan);
					System.out.println("*****loan.getLoansid() == " + loan.getLoansid());
//					loan_detail.setLoansid(loan.getLoansid());
					
					loan_detail.setLogin_name(loan.getLogin_name());
//					loan_detail.setPlatform_id(loan.getPlatform().getPlatform_id());
					loan_detail.setPlatform(loan.getPlatform());
					loan_detail.setStatus(0);
					loan_detail.setLoan_date(loan.getLoan_date());
					loan_detail.setOwn_amt(divAmt);
					loan_detail.setPeriod(i+1);
					loan_detail.setTotal_period(loan.getDuration());
					

					ins_amt = remainAmt * ins_rate /1200;
					loan_detail.setInterest_amt(ins_amt);    //利息
					remainAmt -= divAmt;
					
					fee_amt = ins_amt * loan.getFee_rate() /100;  //管理费
					loan_detail.setFee_amt(fee_amt);
					loan_detail.setInterest_rate(ins_rate);

					cal.add(Calendar.MONTH, 1);
					loan_detail.setExpire_date(cal.getTime());   //回款日期
					loan_details.add(loan_detail);
				}
				break;
			case 1: //等额本息
				monthactive = ins_rate/1200;
				totalmoney = loan.getAmount();
				totalmonth = loan.getDuration();
				//每月还款额
				double monthmoney=totalmoney*monthactive*(Math.pow((1+monthactive),totalmonth))/(Math.pow((1+monthactive),totalmonth)-1);
				for(int i=0;i<loan.getDuration();i++)
				{
					//每月利息额
					double monthinterestmoney=totalmoney*monthactive*(Math.pow((1+monthactive),totalmonth)-Math.pow((1+monthactive),i))/(Math.pow((1+monthactive),totalmonth)-1);
					//每月本金
					double principalmoney=monthmoney-monthinterestmoney;
					
					Loan_detail loan_detail=new Loan_detail();
					loan_detail.setLoan(loan);
					System.out.println("*****loan.getLoansid() == " + loan.getLoansid());
//					loan_detail.setLoansid(loan.getLoansid());
					
					loan_detail.setLogin_name(loan.getLogin_name());
//					loan_detail.setPlatform_id(loan.getPlatform().getPlatform_id());
					loan_detail.setPlatform(loan.getPlatform());
					loan_detail.setStatus(0);
					loan_detail.setLoan_date(loan.getLoan_date());
					loan_detail.setOwn_amt(principalmoney);
					loan_detail.setPeriod(i+1);
					loan_detail.setTotal_period(loan.getDuration());
					loan_detail.setInterest_amt(monthinterestmoney);    //利息
					
					fee_amt = monthinterestmoney * loan.getFee_rate() /100;  //管理费
					loan_detail.setFee_amt(fee_amt);
					loan_detail.setInterest_rate(ins_rate);

					cal.add(Calendar.MONTH, 1);
					loan_detail.setExpire_date(cal.getTime());   //回款日期
					loan_details.add(loan_detail);
				}
				break;
			case 2: //月还息到期还本
				ins_amt = loan.getAmount() * ins_rate /1200; //计算每个月的利息
				for(int i=0;i<loan.getDuration();i++)
				{
					Loan_detail loan_detail=new Loan_detail();
					loan_detail.setLoan(loan);
//					loan_detail.setLoansid(loan.getLoansid());
					loan_detail.setLogin_name(loan.getLogin_name());
//					loan_detail.setPlatform_id(loan.getPlatform().getPlatform_id());
					loan_detail.setPlatform(loan.getPlatform());
					loan_detail.setStatus(0);
					loan_detail.setLoan_date(loan.getLoan_date());
					loan_detail.setInterest_amt(ins_amt);    //利息
					loan_detail.setPeriod(i+1);
					loan_detail.setTotal_period(loan.getDuration());
					
					fee_amt = ins_amt * loan.getFee_rate() /100;  //管理费
					loan_detail.setFee_amt(fee_amt);
					loan_detail.setInterest_rate(ins_rate);

					cal.add(Calendar.MONTH, 1);
					loan_detail.setExpire_date(cal.getTime());   //回款日期
					if (i==loan.getDuration()-1)
					{
						loan_detail.setOwn_amt(loan.getAmount());                  //最后一个月还本
					}
					else
					{
						loan_detail.setOwn_amt(0);
					}
					loan_details.add(loan_detail);
				}
				break;
			case 3: //到期还本息
				ins_amt = loan.getAmount() * loan.getDuration()/12 * ins_rate /100;  //计算总共的利息
				Loan_detail loan_detail=new Loan_detail();
				loan_detail.setLoan(loan);
	//			loan_detail.setLoansid(loan.getLoansid());
				loan_detail.setLogin_name(loan.getLogin_name());
//				loan_detail.setPlatform_id(loan.getPlatform().getPlatform_id());
				loan_detail.setPlatform(loan.getPlatform());
				loan_detail.setStatus(0);
				loan_detail.setLoan_date(loan.getLoan_date());
				loan_detail.setInterest_amt(ins_amt);    //利息
				loan_detail.setPeriod(1);
				loan_detail.setTotal_period(1);

				fee_amt = ins_amt * loan.getFee_rate() /100;  //管理费
				loan_detail.setFee_amt(fee_amt);
				loan_detail.setInterest_rate(ins_rate);
				
				cal.add(Calendar.MONTH, loan.getDuration());
				loan_detail.setExpire_date(cal.getTime());   //回款日期
				loan_detail.setOwn_amt(loan.getAmount());                  
				loan_details.add(loan_detail);
				break;
			}
		}

		if (daily.intValue() == 1)  //天标
		{
			switch(return_type.intValue()){
			case 0:   //等额本金
			case 1: //等额本息
			case 2: //月还息到期还本
			case 3: //到期还本息
				ins_amt = loan.getAmount() * loan.getDuration()/360 * ins_rate /100;  //计算总共的利息
				Loan_detail loan_detail=new Loan_detail();
				loan_detail.setLoan(loan);
	//			loan_detail.setLoansid(loan.getLoansid());
				loan_detail.setLogin_name(loan.getLogin_name());
//				loan_detail.setPlatform_id(loan.getPlatform().getPlatform_id());
				loan_detail.setPlatform(loan.getPlatform());
				loan_detail.setStatus(0);
				loan_detail.setLoan_date(loan.getLoan_date());
				loan_detail.setInterest_amt(ins_amt);    //利息
				loan_detail.setPeriod(1);
				loan_detail.setTotal_period(1);

				fee_amt = ins_amt * loan.getFee_rate() /100;  //管理费
				loan_detail.setFee_amt(fee_amt);
				loan_detail.setInterest_rate(ins_rate);
				
				cal.add(Calendar.DATE, loan.getDuration());
				loan_detail.setExpire_date(cal.getTime());   //回款日期
				loan_detail.setOwn_amt(loan.getAmount());                  
				loan_details.add(loan_detail);
				break;
			}
		}
		
		return loan_details;
		
	}
}
