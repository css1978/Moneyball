package com.loanly.util;

public class CalInsAmt {
	public double proceed(double loan_amt,float ins_rate,int ins_rate_type,int duration,int daily,int sec,int return_type){
		double ins_amt=0;
		float monthactive=0;  //月利率
		double totalmoney=0;  //总金额
		Integer totalmonth;   //期限
		double monthinterestmoney;
		if (ins_rate_type == 0)  //年利率
		{
			ins_rate = ins_rate;
		}
		else if (ins_rate_type == 1)  //月利率
		{
			ins_rate = ins_rate * 12 ;
		}
		else													//日利率
		{
			ins_rate = ins_rate * 365 ;
		}
		
		if (daily == 0 && sec == 0)  //月标
		{
			switch(return_type){
			case 0:   //等额本金
				double divAmt=0;
				double remainAmt=0;
				divAmt = loan_amt / duration;
				remainAmt = loan_amt;
				for(int i=0;i<duration;i++)
				{
					ins_amt += remainAmt * ins_rate/1200;
					remainAmt -= divAmt;
				}
				break;
			case 1: //等额本息
				monthactive = ins_rate/1200;
				totalmoney = loan_amt;
				totalmonth = duration;
				ins_amt =0;
				for(int i=0;i<totalmonth;i++)
				{
					//每月利息额
					monthinterestmoney=totalmoney*monthactive*(Math.pow((1+monthactive),totalmonth)-Math.pow((1+monthactive),i))/(Math.pow((1+monthactive),totalmonth)-1);
					ins_amt += monthinterestmoney;
				}
				break;
			case 2: //月还息到期还本
			case 3: //到期还本息
				ins_amt = loan_amt * duration/12 * ins_rate/100;
				break;
			}
		}
		
		if (daily == 1 && sec == 0)  //天标 目前只支持到期还款
		{
			switch(return_type){
			case 0:   //等额本金
			case 1: //等额本息
			case 2: //月还息到期还本
			case 3: //到期还本息
				ins_amt = loan_amt * duration/360 * ins_rate/100;
				break;
			}
		}
		
		return ins_amt;
		
	}
}
