<%--
  Created by IntelliJ IDEA.
  User: AAA
  Date: 2021/6/30
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>denglu</title>
</head>
<body>
<s:text name="登陆页面"></s:text>
        <s:form action="addUser" method="get">
            <s:textfield name="username" key="login" id="userName" />
            <s:password name="password" key="pass" id="password" />
            <s:submit value="登陆" label="登陆" />
        </s:form>
</body>
</html>
