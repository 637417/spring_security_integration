package com.spring.security.integration.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.spring.security.integration.sys.dao.SysLogMapper;
import com.spring.security.integration.sys.model.SysLog;
import com.spring.security.integration.sys.model.SysUser;

@Service("sysLogServerImpl")
public class SysLogServiceImpl {
    
    public static Logger log = Logger.getLogger(SysLogServiceImpl.class);
    
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Resource(name = "sysLogMapper")
    private SysLogMapper sysLogMapper;
    
    /**
     * 有参无返回值的方法
     * 
     * @Title: logArg
     * @return void 返回类型
     */
    public void logArg(JoinPoint point) {
        // 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        System.out.println("目标参数列表：");
        if (args != null) {
            for (Object obj : args) {
                log.info(obj + ",");
            }
            System.out.println();
        }
    }
    
    /**
     * 有参并有返回值的方法
     * 
     * @Title: logArgAndReturn
     * @return returnObj 返回类型
     */
    public void logArgAndReturn(JoinPoint point, Object returnObj) {
        SysLog sysLog = new SysLog();
        sysLog.setCreateTime(sdf.format(new Date()));
        MethodSignature ms = (MethodSignature)point.getSignature();
        if ("loadUserByUsername".equals(ms.getMethod().getName())) {
            return;
        }
        sysLog.setOperation(ms.getMethod().getName());
        
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setVisitIp(getIpAddress(request));
        
        Object[] args = point.getArgs();
        if (args != null) {
            String jsonString = JSON.toJSONString(args);
            String str = jsonString.length() > 1024 ? jsonString.substring(0, 1010) + "...}]" : jsonString;
            sysLog.setRequestParm(str);
        }
        if (returnObj != null) {
            String jsonString = JSON.toJSONString(returnObj);
            String str = jsonString.length() > 1024 ? jsonString.substring(0, 1010) + "...}]" : jsonString;
            sysLog.setResult(str);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SysUser) {
            SysUser user = (SysUser)principal;
            sysLog.setUserId(user.getUserId());
            sysLog.setUserName(user.getUsername());
        }
        if (sysLog != null) {
            sysLogMapper.insertSysLog(sysLog);
        }
    }
    
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && ip != "" && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip != "" && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
    
}
