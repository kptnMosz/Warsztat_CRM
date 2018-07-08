<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 04.07.18
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title>Usuń pojazd</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>

<%@ include file="../fragments/header.jsp" %>

<br> dane samochodu:
${vehicle.id}/${vehicle.model}/${vehicle.brand}/${vehicle.registration}
<c:if test="${vehicle.id!=0 && not empty vehicle}">
    <form method="post" action="/VehicleClear">

        <button type="submit" name="aprove" value=${vehicle.id}>Na pewno?</button>
    </form>
</c:if>
<c:if test="${empty param.id}">

    brak wybranego samochodu!
</c:if>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>