<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
    导入标签库
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆页面</title>
</head>
<body>
<!--
    国际化的区域信息是决定国际化显示的因素 -> org.springframework.web.servlet.DispatcherServlet.localeResolver
-->
<h1>
    <fmt:message key="welcomeInfo"/>
</h1>
<form action="">
    <fmt:message key="username"/>:<input/><br/>
    <fmt:message key="password"/>:<input/><br/>
    <input type="submit" value="<fmt:message key="loginBtn"/>"/>
</form>
</body>
</html>