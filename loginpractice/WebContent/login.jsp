<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"content="text/html;charset=UTF-8">
<title>用户登录</title>
</head>
<body>

	<form name="reg" action="/loginpractice/LoginServlet" method="post">
	<div id="div" style="width:1000px; height:1000px; border:1px solid #000;">
		<div style="margin-top:82px;text-align: center;">
		用户名：<input name="username" type="text" /><br /> 
		密&nbsp;&nbsp;码： <input name="password" type="password" /><br /> 
		验证码：<input name="check_code" type="text" /> <br />
		
             <img src="/loginpractice/CheckServlet"><br />		
		     <input type="submit"value="提交" id="bt" /><br />	
		</div>
	</div>
	</form>

</body>
</html>