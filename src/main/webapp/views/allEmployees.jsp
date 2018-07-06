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
      Id pracownika= <th>${employee.id}</th>,
        Imię: <td>${employee.name}</td>,
        Nazwisko: <td>${employee.surname}</td>,
       Adres zamieszkania: <td>${employee.adress}</td>,
        Nr. tel: <td>${employee.phone}</td>,
        Notatka:<td>${employee.notes}</td>,
       Stawka godzinowa: <td>${employee.wage}</td><br>


    </tr>
</c:forEach>

</body>
</html>
