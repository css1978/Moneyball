package com.loanly.controller;

import java.sql.Date;
import java.util.List;

import com.loanly.model.Loan;

public interface LoanDao {
    public List<Loan> queryForPage(String user_name,Date loanStartDate,Date loanEndDate,String platform_id,String status,final int offset,final int length);
    
    /**
     * 查询所有记录数
     * @param hql 查询的条件
     * @return 总记录数
     */
    public int getAllRowCount(String user_name,Date loanStartDate,Date loanEndDate,String platform_id,String status);
}