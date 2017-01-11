<%@page import="com.spring.security.integration.base.controller.CheckController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/base.jsp" %>
<%@ include file="/WEB-INF/jsp/meta.jsp" %>
<title>欢迎登录</title>
</head>
<body>
	<%
	    CheckController te = new CheckController();
	%>
	用户{
	<%=te.getCurrentUser().getUsername()%>
	}您好！欢迎登录
	<input type="button" onclick="logout()" value="退出">
</body>
<script type="text/javascript">
	window.onunload = function() {
		logout();
	};
	function logout() {
		if (confirm("您确定要退出？")) {
			$.ajax({
				type : 'GET',
				url : '<%=basePath %>/logout',
				success : function(data) {
					window.location = '<%=basePath %>/login';
				}
			});
		} 
	}
</script>
</html>