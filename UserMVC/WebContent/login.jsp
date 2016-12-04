<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor=#00FFFF>

<form action="handleDatabase" method="post">
	<table border="1">
		<tr>
			<th>请您登陆</th>
		</tr>
		<tr>
			<td>登陆名称:<input type="text" name="logname"></td>
		</tr>
		<tr>
			<td>输入密码:<input type="text" name="password"></td>
		</tr>
	</table>
	<input type="hidden" name="type" value="login">
	<input type="submit" name="submit" value="提交">
</form>

</body>
</html>