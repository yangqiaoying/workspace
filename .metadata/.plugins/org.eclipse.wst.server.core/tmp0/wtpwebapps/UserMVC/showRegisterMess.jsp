<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:useBean id="database" class="com.yourbean.UserBean" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor=#00FFFF>
<jsp:include page="head.txt" />
<div style="text-align:center;">
	<div  class="frend-link" style="width:280px;text-align:left;margin:0 auto;">
	<span>
		注册成功<br>
		注册的会员名称:<jsp:getProperty property="logname" name="database"/><br>
		注册的电子邮件:<jsp:getProperty property="email" name="database"/><br>
		注册的联系电话:<jsp:getProperty property="phone" name="database"/><br>
		您的简历和交友标准:<br>
		<textarea rows="5" cols="27">
		<jsp:getProperty property="message" name="database"/>
		</textarea>
	</span>
</body>
</html>