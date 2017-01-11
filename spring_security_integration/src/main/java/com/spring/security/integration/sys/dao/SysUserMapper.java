package com.spring.security.integration.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.security.integration.sys.model.SysUser;

@Repository("sysUserMapper")
public interface SysUserMapper {
    /**
     * 用户删除
     */
    public int deleteSysUser(Integer userId);
    
    /**
     * 添加用户
     */
    public int insertSysUser(SysUser record);
    
    /**
     * 根据userId或用户名查询用户信息，默认全部查询
     */
    public List<SysUser> selectSysUser(SysUser record);
    
    /**
     * 更新用户信息
     */
    public int updateSysUserSelective(SysUser record);
    
    /**
     * 更新用户信息
     */
    public int updateSysUser(SysUser record);
}