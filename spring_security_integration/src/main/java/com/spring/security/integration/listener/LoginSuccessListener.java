package com.spring.security.integration.listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spring.security.integration.base.util.SpringBeanUtil;
import com.spring.security.integration.sys.dao.SysLogMapper;
import com.spring.security.integration.sys.model.SysLog;
import com.spring.security.integration.sys.model.SysUser;
import com.spring.security.integration.sys.service.impl.SysLogServiceImpl;

/**
 * 系统登录日志
 * 
 * @Title: LoginSuccessListener.java
 * @Package com.spring.security.integration.listener
 * @Description: TODO(系统登录日志)
 * @date 2016年11月21日 下午2:30:15
 * @version V1.0
 */
@Service("loginSuccessListener")
public class LoginSuccessListener implements AuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
         response.sendRedirect("/spring_security_integration/welcome");
        SysLogMapper sysLogMapper = (SysLogMapper)SpringBeanUtil.getBean("sysLogMapper");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SysUser) {
            SysUser user = (SysUser)principal;
            SysLog sysLog = new SysLog();
            sysLog.setCreateTime(SysLogServiceImpl.sdf.format(new Date()));
            sysLog.setUserId(user.getUserId());
            sysLog.setUserName(user.getUsername());
            sysLog.setOperation("用户登录");
            sysLog.setRemarks("用户" + user.getUsername() + "成功登录");
            sysLog.setResult("true");
            sysLog.setVisitIp(SysLogServiceImpl.getIpAddress(request));
            sysLog.setRequestParm(JSON.toJSONString(user.getUsername()));
            sysLogMapper.insertSysLog(sysLog);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(JSON.toJSONString(user));
                out.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
