<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 04.07.18
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja pojazdu</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>

<%@ include file="../fragments/header.jsp" %>

<c:if test="${empty vehicle}">

    brak wybranego samochodu!
</c:if>

<br> dane samochodu:
<br>${editable}

<c:if test="${not empty vehicle}">
    <form method="post" action="/VehicleDetails">
        <table>
            <tr>
                <td>id:</td>
                <td>${vehicle.id} <input type="number" name="id" value="${vehicle.id}" hidden/></td>
            </tr>
            <tr>
                <td>model:</td>
                <td><input name="model" type="text" value="${vehicle.model}" ${editable}readonly></td>
            </tr>
            <tr>
                <td>brand:</td>
                <td><input name="brand" type="text" value="${vehicle.brand}" ${editable}readonly></td>
            </tr>
            <tr>
                <td>registration:</td>
                <td><input name="registration" type="text" value="${vehicle.registration}" ${editable}readonly></td>
            </tr>
            <tr>
                <td>produced in::</td>
                <td><input name="produced" type="text" value="${vehicle.produced}" readonly></td>
            </tr>
            <tr>
                <td>next inspection:</td>
                <td><input name="nextInspection" type="date" value="${vehicle.nextInspection}" ${editable}readonly></td>
            </tr>
            <tr>
                <td>owner:</td>
                <td><input name="owner" type="number" value="${vehicle.customerId}" ${editable}readonly></td>
            </tr>
            <c:if test="${editable}">
                <tr>
                    <td>
                        <button type="submit" name="aprove" value=${vehicle.id}>zmień dane</button>
                    </td>
                </tr>
            </c:if>
        </table>
    </form>
</c:if>

<h5>Lista napraw pojazdu:</h5>
<table>
    <tr>
        <th>id</th>
        <th>status</th>
        <th>data zlecenia:</th>
        <th>opis problemu</th>
        <th>opis rozwiazania</th>
        <th>cena</th>

    </tr>
    <c:forEach var="order" items="${orders}">

    <tr>
        <td>${order.id}</td>
        <td>${order.statusId}</td>
        <td>${order.acceptanceToRepair}</td>
        <td>${order.problemDesc}</td>
        <td>${order.fixDesc}</td>
        <td>${order.price}</td>
        <td><a href="/OrderDetails?orderid=${order.id}">przejdź do zlecenia</a></td>

    </tr>


    </c:forEach>
</table>

    <%@include file="../fragments/footer.jsp" %>
</body>
</html>