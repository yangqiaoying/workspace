<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
	<font size=4>
		<form action="" method=post>
				汽车牌号:<input type="text" name="number">(如:"陕A12345")</input>
			<br>汽车名称:<input type="text" name="name">(如:"BYD牌")</input>
			<br>生产日期:<input type="text" name="madetime">(如:"2016.10")</input>
			<br><input type="submit" value="提交"></input>
		</form>
		<jsp:useBean id="base" class="tom.jiafei.Car" scope="page" />
		<jsp:setProperty name="base" property="*"/>
		<%base.insertData(); %>
		<jsp:getProperty name="base" property="queryResult" />
	</font>
</body>
</html>