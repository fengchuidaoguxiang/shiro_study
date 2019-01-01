<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/12/31
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h2>hello servlet</h2> <a href="/logout.html">退出登录</a>
  <shiro:hasPermission name="menu:list">
    <a href="#">menu</a>
  </shiro:hasPermission>

  <shiro:hasRole name="admin">
    <a href="#">role</a>
  </shiro:hasRole>
  </body>
</html>
