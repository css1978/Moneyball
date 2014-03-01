package com.loanly.controller;

import java.sql.Date;
import java.util.List;

import com.loanly.model.Platform;

public interface PlatformDao {
    public List<Platform> queryForPage(String user_name,final int offset,final int length);
    
    /**
     * 查询所有记录数
     * @param hql 查询的条件
     * @return 总记录数
     */
    public int getAllRowCount(String user_name);
}