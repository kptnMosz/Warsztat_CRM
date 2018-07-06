<%--
  Created by IntelliJ IDEA.
  User: samael
  Date: 06.07.18
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../fragments/header.jsp"  %>
<form method='post' action=''>

<div><label>Name: <br><input type='text' name='name' required /> </label></div>
<div><label>Surname: <br><input type='text' name='surname' required /> </label></div>
<div><label>Address: <br><input type='text' name='adress' required /> </label></div>
<div><label>Phone number: <br><input type='text' name='phone' required /> </label></div>
<div><label>Information about employee: <br><input type='text' name='notes' required /> </label></div>
<div><label>Hourly payment: <br><input type='number' name='wage' min=0 required /> </label></div>
<div><input type='submit' value='submit'/> </div>
</form>

<%@ include file="../fragments/footer.jsp" %>

</body>
</html>
