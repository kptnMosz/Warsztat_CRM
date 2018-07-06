<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 03.07.18
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista pojazdów</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<p>
    <a href="/VehicleNew${empty param.customer? "" : "?custid="} ${empty param.customer? "" : param.customer}">Dodaj Pojazd</a>
</p>
<table>
    <c:forEach var="vehicle" items="${vehicles}">
        <tr>

            <td>
                <a href="../VehicleDetails?id=${vehicle.id}">
                        ${vehicle.model} / ${vehicle.brand} / ${vehicle.registration}
                </a>
            </td>

            <td>
                <a href="../VehicleDetails?id=${vehicle.id}&mode=edit">
                    edytuj
                </a>
            </td>
            <td>
                <a href="../VehicleClear?id=${vehicle.id}">
                    usuń
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
