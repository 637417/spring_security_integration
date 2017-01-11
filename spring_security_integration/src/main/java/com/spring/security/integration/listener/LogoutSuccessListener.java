package com.spring.security.integration.listener;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.spring.security.integration.base.util.SpringBeanUtil;
import com.spring.security.integration.sys.dao.SysLogMapper;
import com.spring.security.integration.sys.model.SysLog;
import com.spring.security.integration.sys.model.SysUser;
import com.spring.security.integration.sys.service.impl.SysLogServiceImpl;

/**
 * 系统退出日志
 * 
 * @Title: LoginSuccessListener.java
 * @Package com.spring.security.integration.listener
 * @Description: TODO(系统退出日志)
 * @date 2016年11月21日 下午2:30:15
 * @version V1.0
 */
@Service("logoutSuccessListener")
public class LogoutSuccessListener implements LogoutSuccessHandler {
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect("/spring_security_integration/login");
        SysLogMapper sysLogMapper = (SysLogMapper)SpringBeanUtil.getBean("sysLogMapper");
        if (authentication != null) {
            SysUser user = (SysUser)authentication.getPrincipal();
            SysLog sysLog = new SysLog();
            sysLog.setCreateTime(SysLogServiceImpl.sdf.format(new Date()));
            sysLog.setUserId(user.getUserId());
            sysLog.setUserName(user.getUsername());
            sysLog.setOperation("用户退出");
            sysLog.setResult("true");
            sysLog.setRemarks("用户" + user.getUsername() + "成功退出");
            sysLog.setVisitIp(SysLogServiceImpl.getIpAddress(request));
            sysLog.setRequestParm(JSON.toJSONString(user.getUsername()));
            sysLogMapper.insertSysLog(sysLog);
        }
    }
    
}
