<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 29.06.18
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warsztat_CRM</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<br>
<h4>Otwarte zlecenia:</h4>
<table>
    <tr>
        <th>status</th>
        <th>car</th>
        <th>employee</th>
        <th>description</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>

            <td>
                <a href="../OrderDetails?id=${order.id}">
                        ${order.statusId}
                </a>
            </td>
            <td>
                <a href="../OrderDetails?id=${order.id}">
                        ${order.repairedVehicleId}
                </a>
            </td>
            <td>
                <a href="../OrderDetails?id=${order.id}">
                        ${order.employeeId}
                </a>
            </td>

            <td>
                <a href="../OrderDetails?id=${order.id}">
                        ${order.problemDesc}
                </a>
            </td>

        </tr>
    </c:forEach>
</table>


<%@include file="../fragments/footer.jsp" %>

</body>
</html>
