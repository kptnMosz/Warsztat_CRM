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
    <title>Zlecenie</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<br>
<h4>Szczegóły zlecenia:</h4>
<form method="post" action="/OrderNew">
    <table>
        <tr>
            <th>nazwa pola</th>
            <th>aktualna wartosc</th>
            <th>nowa wartość</th>
        </tr>
        <tr>
            <td>
                id:
            </td>
            <td>

            </td>
            <td></td>
        </tr>

        <tr>
            <td>
                status naprawy:
            </td>
            <td>
                nowe zgłoszenie
            </td>
            <td>

                <select name="status">
                        <option value="1">1/Przyjety</option>
                </select>

            </td>
        </tr>
        <tr>
            <td>
                naprawiany samochód:
            </td>
            <td>

            </td>
            <td><input name="vehicleId" type="number" value="${vahicleId.value}" /></td>
        </tr>

        <tr>
            <td>
                pracownik:
            </td>
            <td>
                <a href="../EmployeeDetails${not empty param.employeeId? "?employeeId=":""} ${param.employeeId}">
                    ${param.employeeId}
                </a>
            </td>
            <td>
                <input type="number" value="${param.employeeId}">
            </td>
        </tr>
        <tr>
            <td>
                opis problemu:
            </td>
            <td>

            </td>
            <td>
                <input type="text" name="problemDesc">
            </td>
        </tr>
        <tr>
            <td>
                opis rozwiazania:
            </td>
            <td>

            </td>
            <td>
                <input type="text" name="fixDesc">
            </td>
        </tr>
        <tr>
            <td>
                planowana data naprawy:
            </td>
            <td>

            </td>
            <td>
                <input type="date" name="plannedFix" />
            </td>
        </tr>
        <tr>
            <td>
                rozpoczęcie naprawy:
            </td>
            <td>

            </td>
            <td>
                <input type="date" name="startFix" />
            </td>
        </tr>
        <tr>
            <td>
                cena:
            </td>
            <td>

            </td>
            <td>
                <input type="number" step="0.01" name="price" />
            </td>
        </tr>
        <tr>
            <td>
                koszt części:
            </td>
            <td>

            </td>
            <td>
                <input type="number" step="0.01" name="partsCost" />
            </td>
        </tr>

    </table>
    <button type="submit">Nowe zlecenie</button>
</form>

<%@include file="../fragments/footer.jsp" %>

</body>
</html>
