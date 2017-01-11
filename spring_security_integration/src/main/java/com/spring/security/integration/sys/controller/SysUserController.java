package com.spring.security.integration.sys.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.spring.security.integration.base.controller.CheckController;
import com.spring.security.integration.base.util.JsonUtil;
import com.spring.security.integration.base.util.MD5Utils;
import com.spring.security.integration.base.util.StringUtil;
import com.spring.security.integration.sys.model.SysUser;
import com.spring.security.integration.sys.service.SysUserService;

/**
 * 用户信息控制器
 * 
 * @Title: SysUserController.java
 * @Package com.spring.security.integration.sys.controller
 * @Description: TODO(用户信息控制器)
 * @date 2016年11月7日 上午11:28:56
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/sysManage/sysUser", produces = "text/html;charset=UTF-8")
public class SysUserController extends CheckController {
    
    public static Logger log = Logger.getLogger(SysUserController.class);
    
    @Resource(name = "sysUserServiceImpl")
    public SysUserService sysUserService;
    
    @ResponseBody
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public Object userCheckLogin(String username) {
        SysUser user = getCurrentUser();
        if (user != null) {
            return JSON.toJSONString(user);
        } else {
            return "no ,please login";
        }
    }
    
    /**
     * 
     * @title：saveOrEditSysUser
     * @author：蔡波
     * @email：caibo@zhichenhaixin.com
     * @description：用户信息添加或编辑
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrEditSysUser", method = RequestMethod.POST)
    public Object saveOrEditSysUser(SysUser record) {
        try {
            if (StringUtil.isBlank(record.getUserId())) {
                if (StringUtil.isBlank(record.getUsername())) {
                    return JsonUtil.getFailJsonMsg("用户名不能为空");
                }
                Pattern patternUserNmae = Pattern.compile("[a-zA-z][a-zA-Z0-9_]{2,9}");
                if (!patternUserNmae.matcher(record.getUsername()).matches()) {
                    return JsonUtil.getFailJsonMsg("用户名必须以字母开头并且长度在2~11个字符");
                }
                List<SysUser> sysUserList = sysUserService.searchSysUser(record);
                if (sysUserList != null && sysUserList.size() > 0) {
                    return JsonUtil.getFailJsonMsg("该用户已存在");
                }
                if (StringUtil.isBlank(record.getPassword())) {
                    return JsonUtil.getFailJsonMsg("密码不能为空");
                }
                Pattern patternPwd = Pattern.compile("[a-zA-Z0-9_]{4,18}");
                if (!patternPwd.matcher(record.getPassword()).matches()) {
                    return JsonUtil.getFailJsonMsg("密码长度必须在4~18个字符");
                }
                if (StringUtil.isBlank(record.getStatus())) {
                    return JsonUtil.getFailJsonMsg("状态不能为空");
                }
                record.setPassword(MD5Utils.encodePasswordByMD5(record.getPassword()));
                sysUserService.saveSysUser(record);
            } else {
                record.setPassword(MD5Utils.encodePasswordByMD5(record.getPassword()));
                sysUserService.editSysUser(record);
            }
            return JsonUtil.getSuccessJsonMsg("操作成功", null);
        } catch (Exception e) {
            log.error("用户信息添加或编辑异常！", e.fillInStackTrace());
            return JsonUtil.getFailJsonMsg("用户信息添加或编辑异常");
        }
    }
    
    /**
     * 
     * @title：searchSysUser
     * @author：蔡波
     * @email：caibo@zhichenhaixin.com
     * @description：根据userId或用户名查询用户信息，默认全部查询
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchSysUser", method = RequestMethod.GET)
    public Object searchSysUser(SysUser record) {
        try {
            List<SysUser> sysUserList = sysUserService.searchSysUser(record);
            return JsonUtil.getSuccessJsonMsg("数据获取成功", sysUserList);
        } catch (Exception e) {
            log.error("获取用户信息异常！", e.fillInStackTrace());
            return JsonUtil.getFailJsonMsg("数据查询异常");
        }
    }
    
    /**
     * 
     * @title：deleteSysUser
     * @author：蔡波
     * @email：caibo@zhichenhaixin.com
     * @description：根据userId删除用户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSysUser", method = RequestMethod.GET)
    public Object deleteSysUser(Integer userId) {
        try {
            sysUserService.deleteSysUser(userId);
            return JsonUtil.getSuccessJsonMsg("操作成功", null);
        } catch (Exception e) {
            log.error("删除用户信息异常！", e.fillInStackTrace());
            return JsonUtil.getFailJsonMsg("数据删除异常");
        }
    }
    
}
