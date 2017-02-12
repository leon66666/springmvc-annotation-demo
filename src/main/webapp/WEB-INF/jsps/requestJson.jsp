<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
function requestJson(){
	//模拟json格式数据
	var jsonUser=JSON.stringify({"username":"张三丰","sex":"男","address":"中关村"})
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/user/requestJson.do',
		contentType:'application/json;charset=utf-8',
		data:jsonUser,
		success:function(data){
			alert(data.username);
		}
	})
}
function requestPo(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/user/requestPo.do',
		data:'username=张三丰',
		success:function(data){
			alert(data.username);
		}
	})
}
</script>
</head>
<body>
<input type="button" value="请求json，返回json" onclick="requestJson();">
<input type="button" value="请求pojo，返回json" onclick="requestPo();">
</body>
</html>