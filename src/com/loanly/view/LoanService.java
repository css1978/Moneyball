package com.loanly.view;

import java.sql.Date;

import com.loanly.util.PageBean;

public interface LoanService {
	/**
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    public PageBean queryForPage(int pageSize,int currentPage,Date expireStartDate,Date expireEndDate,String platform_id,String status);
}
