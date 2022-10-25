<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/25/2022
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Từ điển</title>
</head>
<body>
<form action="/index" method="get">
    <h1>Tra cứu từ điển (Anh-Việt)</h1>
    <label for="english">English</label>
    <input  id="english" type="text"  name="english" value="${english}" style="width: 300px">
    <label for="translate">Translate</label>
    <input id="translate" type="text"  name="translate" value="${translate}" style="width: 300px">
    <button type="submit" >Dịch</button>

</form>
</body>
</html>
