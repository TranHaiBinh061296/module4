<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/25/2022
  Time: 11:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<h1>Dictionary</h1>
<form method="post" action="/result">
    <input type="search" name="word" placeholder="Enter your English" value="${word}">
    <input type="submit" value="Search">
    <p>${error}</p>
</form>
</body>
</html>