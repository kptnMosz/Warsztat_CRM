<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: samael
  Date: 03.07.18
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="pl">
<head>
    <title>Title</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<h4>Lista pracowników </h4>
<%@ include file="../fragments/header.jsp" %>
<%--<a href="/AddEmployee${empty param.employeeid? "" : "?employeeid="} ${empty param.}"--%>
<p>
    <a href="/VehicleNew${empty param.customerid? "" : "?customerid="} ${empty param.customerid? "" : param.customerid}">Dodaj Pojazd</a>
</p>

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

<table>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <%--<td>--%>
                <%--<a href="../EmployeeDetails?employeeid=${employee.id}">--%>
                        <%--${employee.name} / ${employee.surname} / ${employee.adress}--%>
                <%--</a>--%>
            <%--</td>--%>

            <td>
                <a href="../ModifyEmployee=${employee.id}?employeeid&mode=edit">
                    Edit
                </a>
            </td>
            <td>
                <a href="../DeleteEmployee=${employee.id}?employeeid">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
