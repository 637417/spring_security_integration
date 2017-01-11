package com.spring.security.integration.sys.dao;

import org.springframework.stereotype.Repository;

import com.spring.security.integration.sys.model.SysLog;

@Repository("sysLogMapper")
public interface SysLogMapper {
    /**
     * 根据用户名模糊查询，默认查询全部
     * 
     * @Title: selectSysLog
     * @return SysLog 返回类型
     */
    public SysLog selectSysLog(SysLog record);
    
    /**
     * 系统日志添加接口
     * 
     * @Title: insertSysLog
     * @return int 返回类型
     */
    public int insertSysLog(SysLog record);
}