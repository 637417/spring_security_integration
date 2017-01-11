package com.spring.security.integration.sys.service;

import java.util.List;

import com.spring.security.integration.sys.model.SysUser;

public interface SysUserService {
    /**
     * 用户删除
     */
    public void deleteSysUser(Integer userId);
    
    /**
     * 添加用户
     */
    public void saveSysUser(SysUser record);
    
    /**
     * 编辑用户
     */
    public void editSysUser(SysUser record);
    
    /**
     * 根据userId或用户名查询用户信息，默认全部查询
     */
    public List<SysUser> searchSysUser(SysUser record);
    
}
