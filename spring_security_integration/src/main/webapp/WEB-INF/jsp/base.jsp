<%
/*	获得webContent路径*/
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!-- BASE CSS -->
<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />

<!-- BASE JAVASCRIPT -->
<script src="<%=basePath%>/static/js/jquery.min.js"></script>
<script src="<%=basePath%>/static/js/jquery.form.js"></script>

