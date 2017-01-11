package com.spring.security.integration.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.integration.sys.dao.SysUserMapper;
import com.spring.security.integration.sys.model.SysUser;

@Service(value = "userDetailsService")
public class SysUserDetailsServiceImpl implements UserDetailsService {
    
    public Log log = LogFactory.getLog(SysUserDetailsServiceImpl.class);
    
    @Resource(name = "sysUserMapper")
    public SysUserMapper sysUserMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        List<SysUser> list = sysUserMapper.selectSysUser(sysUser);
        if (list==null || list.size() == 0) {
            log.error("该用户名不存在，请重试！");
            throw new UsernameNotFoundException("该用户名不存在，请重试！");
        }
        return list.get(0);
    }
    
}
