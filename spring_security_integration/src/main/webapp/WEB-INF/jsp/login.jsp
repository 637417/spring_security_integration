<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/WEB-INF/jsp/base.jsp" %>
		<%@ include file="/WEB-INF/jsp/meta.jsp" %>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/app.css" />
		<title>Login page</title>
	</head>
	<body>
		<div id="mainWrapper">
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
						<p><font style="color: red;">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</font></p>
						<form id="loginform" class="form-horizontal" action="<%=basePath%>/checkLogin" method="post">
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Enter Username" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
								<input type="password" class="form-control" name="password" id="password" placeholder="Enter Password" required>
							</div>
							<div class="form-actions">
								<input name="login" id="login" type="submit" class="btn btn-block btn-primary btn-default" value="Login">
							</div>
							<label><input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me"  /> 下次自动登录</label>
						</form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>