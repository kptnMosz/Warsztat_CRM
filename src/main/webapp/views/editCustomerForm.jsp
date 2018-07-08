<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 07.07.2018
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title>Edytuj klienta</title>
</head>
<body>
<h1>Edytujesz klienta o id = ${param.customerId}, imie = ${param.customerName} ${param.customerSurname}</h1>
<form action="../EditCustomer" method="POST">
    ID: <input readonly name="id" value="${param.customerId}"/><br>
    Imię: <input type="text" name="name" value="${param.customerName}"/><br>
    Nazwisko: <input type="text" name="surname" value="${param.customerSurname}"/><br>
    Email: <input type="text" name="email" value="${param.customerEmail}"/><br>
    Data urodzenia: <input type="date" name="birthdayDate" value="${param.customerBirthdayDate}"/><br>
    Hasło: <input type="password" name="password"/><br>

    <input type="submit" value="Przeslij"/>
</form>
</body>
</html>
