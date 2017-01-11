package com.spring.security.integration.base.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.security.integration.sys.model.SysUser;

public class CheckController {
	/**
	 * 验证用户是否登录
	* @Title: getCurrentUser 
	* @Description: TODO(执行操作前，进行登录验证) 
	* @return SysUser    返回类型
	 */
	public SysUser getCurrentUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof SysUser){
			return (SysUser) principal;
		}else{
			return null;
		}
	}
}
