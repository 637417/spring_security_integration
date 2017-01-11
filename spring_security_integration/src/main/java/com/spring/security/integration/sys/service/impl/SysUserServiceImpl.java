package com.spring.security.integration.sys.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.spring.security.integration.sys.dao.SysUserMapper;
import com.spring.security.integration.sys.model.SysUser;
import com.spring.security.integration.sys.service.SysUserService;

@Service("sysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {
    
    @Resource(name = "sysUserMapper")
    public SysUserMapper sysUserMapper;
    
    @Override
    public void deleteSysUser(Integer userId) {
        sysUserMapper.deleteSysUser(userId);
    }
    
    @Override
    public void saveSysUser(SysUser record) {
        sysUserMapper.insertSysUser(record);
    }
    
    @Override
    public void editSysUser(SysUser record) {
        sysUserMapper.updateSysUser(record);
    }
    
    @Override
    public List<SysUser> searchSysUser(SysUser record) {
        return sysUserMapper.selectSysUser(record);
    }
    
}
