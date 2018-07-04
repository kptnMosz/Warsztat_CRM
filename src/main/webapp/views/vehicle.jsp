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
    <title>Edycja pojazdu</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../fragments/header.jsp" %>

<table>
    <c:forEach var="vehicle" items="${vehicles}">
        <tr>
            <a href="/views/VehicleDetails&id=${vehicle.id}">
                <td>
                        ${vehicle.model} / ${vehicle.brand} / ${vehicle.registration}
                </td>
            </a>
            <td
        </tr>
    </c:forEach>
</table>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
