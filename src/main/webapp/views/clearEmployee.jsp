<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: samael
  Date: 07.07.18
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>

<%@ include file="../fragments/header.jsp" %>

<br> Dane Pracownika:
${employee.id}/${employee.name}/${employee.surname}/${employee.adress}/${employee.notes}/${employee.wage}
<c:if test="${employee.id!=0 && not empty employee}">
    <form method="post" action="/DeleteEployee">

        <button type="submit" name="aprove" value=${employee.id}>Czy aby na pewno?</button>
    </form>
</c:if>
<c:if test="${empty param.id}">

    Nie wybrałeś/-łaś pracownika!
</c:if>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
