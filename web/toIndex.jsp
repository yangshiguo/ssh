<%--
  Created by IntelliJ IDEA.
  User: SimpleTonOne
  Date: 2018/6/28
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bath = request.getContextPath();
    String bashPath = request.getScheme() + "://" +request.getServerName() +":" + request.getServerPort() +bath +"/";
%>
<html>
<head>
    <title>Title</title>
    <META HTTP-EQUIV="Refresh" CONTENT="0;URL=<%=bashPath%>index">
</head>
<body>

</body>
</html>
