<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/recieveUser.do" method="post">
姓名：<input type="text" name="username" value="${user.username }" id="username">
生日：<input type="text" name="birthday" value="${user.birthday }" id="birthday">
性别：<input type="text" name="sex" value="${user.sex }" id="sex">
地址：<input type="text" name="address" value="${user.address }" id="address">
<input type="submit" value="提交">
</form>


</body>
</html>