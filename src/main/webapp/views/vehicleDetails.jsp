<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 04.07.18
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<html>
<head>
    <title>Edycja pojazdu</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>

<%@ include file="../fragments/header.jsp" %>

<br> dane samochodu:
${vehicle.id}/${vehicle.model}/${vehicle.brand}/${vehicle.registration}
<br>${editable}

<c:if test="${not empty vehicle}">
    <form method="post" action="/VehicleDetails">
        <table>
            <tr>
                <td>id:</td>
                <td>${vehicle.id}</td>
                <td colspan="2">niezmienne</td>
            </tr>
            <tr>
                <td>model:</td>
                <td>${vehicle.model}</td>
                <td>nowa wartość:</td>
                <td><input type="text" value="${vehicle.model}" ${editable}readonly></td>
            </tr>
            <tr>
                <td>
                <button type="submit" name="aprove" value=${vehicle.id}>zmień dane</button>
                </td>
            </tr>
        </table>
    </form>
</c:if>
<c:if test="${empty vehicle}">

    brak wybranego samochodu!
</c:if>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>