<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Simple jsp page</title>
</head>
<body>
<h1>error!!!!!!</h1>
<div><a href="<%=basePath%>/login.jsp">login</a></div>
<div><a href="<%=basePath%>/index.jsp">home</a></div>
</body>
</html>
