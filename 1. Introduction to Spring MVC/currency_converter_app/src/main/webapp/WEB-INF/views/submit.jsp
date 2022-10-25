<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/25/2022
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Converting currency</title>
</head>
<body>
<label for="rate">Tỉ giá</label>
<input  id="rate" type="number"  name="Rate" value="${rate}" style="width: 300px"><br>
<label for="usd">USD</label>
<input id="usd" type="number"  name="amount" value="${usd}" style="width: 300px"><br>
<label for="result">VND</label>
<input id="result" type="number"  name="result"  value="${results}" style="width: 300px">
</body>
</html>
