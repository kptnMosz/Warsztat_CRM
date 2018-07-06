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
    <title>Zlecenia</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<br>
<h4>Zlecenia</h4>
<form method="post" action="/OrderView">
    <fieldset>
        <legend>filtr:</legend>

Customer:<input type="text" value="${filterCustomerId}" name="filterCustomerId"><br />
Status: <input type="text" value="${filterStatusId}" name="filterStatusId"><br />
    </fieldset>
    <%--todo<input type="submit" />--%>
</form>

<table>
    <tr>
        <th>status</th>
        <th>owner</th>
        <th>car</th>
        <th>employee</th>
        <th>description</th>
    </tr>
    <c:forEach var="order" items="${orders}">

       <tr>

            <td>
                <a href="../OrderDetails?orderid=${order.id}">
                        ${order.statusId}
                </a>
            </td>
            <td>
                <a href="../CustomerDetails?customerid=${order.vehicle.customerId}">
                        ${order.vehicle.customerId}
                </a>
            </td>
            <td>
                <a href="../VehicleDetails?vehicleid=${order.repairedVehicleId}">
                        ${order.repairedVehicleId}
                </a>
            </td>
            <td>
                <a href="../EmployeeDetails?employeeid=${order.employeeId}">
                        ${order.employeeId}
                </a>
            </td>

            <td>
                <a href="../OrderDetails?orderid=${order.id}">
                        ${order.problemDesc}
                </a>
            </td>

        </tr>

    </c:forEach>

</table>


<%@include file="../fragments/footer.jsp" %>

</body>
</html>
