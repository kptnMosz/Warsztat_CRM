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
    <title>Raporty</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<br>
<h4>Dostępne raporty:</h4>
<table>
    <tr>

        <td>
            Uproszczony raport wyniku
        </td>
        <td>
            start date: <input type="date" name="start" />
        </td>
        <td>
            end date: <input type="date" name="end" />
        </td>
        <td>
            <input type="submit" name="Uruchom raport">
        </td>
    </tr>


</table>


<%@include file="../fragments/footer.jsp" %>

</body>
</html>
