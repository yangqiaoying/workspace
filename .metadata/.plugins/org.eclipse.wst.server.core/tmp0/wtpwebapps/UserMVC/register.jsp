<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<jsp:include page="head.txt" />
</head>

<body bgcolor=#00FFFF>
	<br><br>
	<div style="text-align:center;">
	<div  class="frend-link" style="width:280px;text-align:left;margin:0 auto;">
	<span>	
		<form action="HandleDatabase" method="post">
			输入您的信息,会员名字必须由字母和数字组成,带*号必须填写.<br>
			会员名称:
			<input type="text" name="logname">*<br>
			设置密码:
			<input type="text" name="password">*<br>
			电子邮件:
			<input type="text" name="email"><br>
			联系电话:
			<input type="text" name="phone"><br>
			输入您的简历和交友标准:<br>
			<textarea rows="5" cols="27" name="message"></textarea>
			<input type="submit" name="submit" value="提交查询内容">
			<input type="hidden" name="type" value="register">
		</form>
	</div></span>
</body>
</html>