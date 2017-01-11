package com.spring.security.integration.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
public class BaseController {
    public Log log = LogFactory.getLog(BaseController.class);
    
    @RequestMapping(value = "{pkg}/{jsp}")
    public String view(@PathVariable("pkg") String pkg, @PathVariable("jsp") String jsp, HttpServletRequest request) {
        return pkg + "/" + jsp;
    }
    
    @RequestMapping(value = "/login")
    public String webLogin(HttpServletRequest request) {
        return "login";
    }
    
    @RequestMapping(value = "/welcome")
    public String index(HttpServletRequest request) {
        return "welcome";
    }
    
    @ResponseBody
    @RequestMapping(value = "/commons/test")
    public String commons(HttpServletRequest request) {
        return "公共接口测试";
    }
}
