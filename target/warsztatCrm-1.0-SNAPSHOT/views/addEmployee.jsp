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
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../fragments/header.jsp"  %>
<form method="post" action="/AddEmployee">
    <table>
        <tr>
            <td>Name:</td>
            <td><input name="name" type="text" /></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><input name="surname" type="text" /></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input name="adress" type="text" /></td>
        </tr>
        <tr>
            <td>Phone number:</td>
            <td><input name="phone" type="text" /></td>
        </tr>
        <tr>
            <td>Information about employee:</td>
            <td><input name="notes" type="text" /></td>
        </tr>
        <tr>
            <td>Hourly payment: </td>
            <td><input name="wage" type="number" /></td>
        </tr>
        <tr>
            <td>
                <button type="submit" name="aprove" >Zaakceptuj Pracownika</button>
            </td>
        </tr>

    </table>
</form>

<%@ include file="../fragments/footer.jsp" %>

</body>
</html>
