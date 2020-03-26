<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<%@ include file="/WEB-INF/views/common/jsCss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

<form action="${ctx}/sys/login" method="post">
<input type="text" name="ename" /></br>
<input type="text" name="empno" /></br>
<input type="submit" value="Login">
</form>

</body>
</html>