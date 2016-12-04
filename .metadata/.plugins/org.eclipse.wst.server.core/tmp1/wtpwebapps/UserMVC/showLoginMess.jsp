<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:useBean id="database" class="com.yourbean.UserBean" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor=#00FFFF>
	<div style="text-align:center;">	
		<h2>登陆成功</h2>
		登陆会员名称:<jsp:getProperty property="logname" name="database"/>
	</div>
</body>
</html>