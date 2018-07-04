<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: samael
  Date: 03.07.18
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${employees}" var="employee">
    <tr>
       id pracownika= <th>${employee.id}</th>,
        imię: <td>${employee.name}</td>,
        nazwisko: <td>${employee.surname}</td>,
       adres zamieszkania: <td>${employee.adress}</td>,
        nr tel: <td>${employee.phone}</td>,
        <td>${employee.notes}</td>,
       oraz zł/h: <td>${employee.wage}</td><br>


    </tr>
</c:forEach>

</body>
</html>
