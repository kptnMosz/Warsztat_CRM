<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 07.07.2018
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title>Wszyscy klienci</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<table>
    <tr>
        <th>ID klienta</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Email</th>
        <th>Data urodzenia</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.surname}</td>
            <td>${customer.email}</td>
            <td>${customer.birthdayDate}</td>
            <td><a href="/views/editCustomerForm.jsp?customerId=${customer.id}&customerName=${customer.name}&customerSurname=${customer.surname}&customerEmail=${customer.email}&customerBirthdayDate=${customer.birthdayDate}">Edytuj</a></td>
            <td><a href="../DeleteCustomer?customerId=${customer.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
