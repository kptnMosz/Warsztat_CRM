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
    <title>Zlecenie</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<br>
<h4>Szczegóły zlecenia:</h4>
<form method="post" action="/OrderDetails">
    <table>
        <tr>
            <th>nazwa pola</th>
            <th>aktualna wartosc</th>
            <th>nowa wartość</th>
        </tr>
        <tr>
            <td>
                id:
            </td>
            <td>
                ${order.id}
            </td>
            <td></td>
        </tr>

        <tr>
            <td>
                status naprawy:
            </td>
            <td>

                ${order.statusId}/
                ${order.statusName}

            </td>
            <td>

                <select name="status">
                    <c:forEach var="status" items="${statusMap}">
                        <option value="${status.key}">${status.value}</option>
                    </c:forEach>
                </select>

            </td>
        </tr>
        <tr>
            <td>
                naprawiany samochód:
            </td>
            <td>
                <a href="VehicleDetails?vehicleid=${order.repairedVehicleId}">
                    ${order.repairedVehicleId}/
                    ${order.vehicle.registration}
                </a>
            </td>
            <td></td>
        </tr>

        <tr>
            <td>
                pracownik:
            </td>
            <td>
                <a href="../EmployeeDetails?employeeid=${order.id}">
                    ${order.employeeId}
                </a>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td>
                opis problemu:
            </td>
            <td>
                ${order.problemDesc}
            </td>
            <td>
                <input type="text" value="${order.problemDesc}">
            </td>
        </tr>
        <tr>
            <td>
                opis rozwiazania:
            </td>
            <td>
                ${order.fixDesc}
            </td>
            <td>
                <input type="text" value="${order.fixDesc}">
            </td>
        </tr>
        <tr>
            <td>
                opis rozwiazania:
            </td>
            <td>
                ${order.fixDesc}
            </td>
            <td>
                <input type="text" name ="fixDesc" value="${order.fixDesc}">
            </td>
        </tr>
        <tr>
            <td>
                planowana data naprawy:
            </td>
            <td>
                ${order.plannedFix}
            </td>
            <td>
                <input type="date" name="plannedFix" value="${order.plannedFix}">
            </td>
        </tr>
        <tr>
            <td>
                rozpoczęcie naprawy:
            </td>
            <td>
                ${order.startFix}
            </td>
            <td>
                <input type="date" name="startFix" value="${order.startFix}">
            </td>
        </tr>
        <tr>
            <td>
                cena:
            </td>
            <td>
                ${order.price}
            </td>
            <td>
                <input type="number" step="0.01" name="price" value="${order.price}">
            </td>
        </tr>
        <tr>
            <td>
                koszt części:
            </td>
            <td>
                ${order.partsCost}
            </td>
            <td>
                <input type="number" step="0.01" name="partsCost" value="${order.partsCost}">
            </td>
        </tr>

    </table>
    <input type="number" name="orderId" value="${order.id}" hidden />
    <button type="submit">Zmień Dane</button>
</form>

<%@include file="../fragments/footer.jsp" %>

</body>
</html>
