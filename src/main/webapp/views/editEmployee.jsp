<%--
  Created by IntelliJ IDEA.
  User: samael
  Date: 07.07.18
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="/fragments/header.jsp"  %>
<h3> Form to edit employee</h3>
<form action='' method='post' />
<div><label>ID: <br><input type="number" name="id" min="0" required/> </label></div>
<div><label>Name: <br><input type='text' name='name' required /> </label></div>
<div><label>Surname: <br><input type='text' name='lastName' required /> </label></div>
<div><label>Address: <br><input type='text' name='address' required /> </label></div>
<div><label>Phone number: <br><input type='text' name='phone' required /> </label></div>
<div><label>Note about employee: <br><input type='text' name='note' required /> </label></div>
<div><label>Hourly payment: <br><input type='number' name='hourlyPayment' min=0 required /> </label></div>
<div><input type='submit' value='submit'/> </div>
</form>
<%@ include file="/fragments/footer.jsp"  %>

</body>
</html>