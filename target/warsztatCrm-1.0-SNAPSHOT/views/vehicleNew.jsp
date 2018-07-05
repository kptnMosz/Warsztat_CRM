<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 05.07.18
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dodaj pojazd</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<form method="post" action="/VehicleNew">
    <table>
        <tr>
            <td>model:</td>
            <td><input name="model" type="text" /></td>
        </tr>
        <tr>
            <td>brand:</td>
            <td><input name="brand" type="text" /></td>
        </tr>
        <tr>
            <td>registration:</td>
            <td><input name="registration" type="text" /></td>
        </tr>
        <tr>
            <td>produced in:</td>
            <td><input name="produced" type="text" /></td>
        </tr>
        <tr>
            <td>next inspection:</td>
            <td><input name="nextInspection" type="date" /></td>
        </tr>
        <tr>
            <td>owner:</td>
            <td><input name="owner" type="number" value=${param.custid} /></td>
        </tr>
            <tr>
                <td>
                    <button type="submit" name="aprove" >dodaj pojazd</button>
                </td>
            </tr>

    </table>
</form>


<%@include file="../fragments/footer.jsp" %>


</body>
</html>
