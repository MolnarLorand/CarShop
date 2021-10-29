<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title> CarStore </title>
</head>
<style>
    body {
        background-color: azure;
    }

    a:link {
        color: sienna;
    }

    a:visited {
        color: blueviolet;
    }

    a:hover {
        color: brown;
    }

    a:active {
        color: aqua;
    }

    div {
        align-content: center;
    }
</style>

<body>
<div align="center">
    <h1>Welcome to this website</h1>
    <b>${jspUser.username}, ${jspUser.email}  </b>
    <a href="/CarShop">Logout</a>
</div>
<br><br>
<div align="center">
    <h2>Car management</h2>
    <a href="new"> Add new car </a>
    &nbsp;&nbsp;&nbsp;
    <a href="list"> List all cars</a>
</div>
<div align="center">
    <br><br>
    <table border="2" cellpadding="15">
        <caption>List of cars</caption>
        <tr>

            <th> ID</th>
            <th> KM</th>
            <th> Price</th>
            <th> Model</th>
            <th> Color</th>
            <th> Actions</th>
        </tr>
        <c:forEach var="car" items="${listCars}">
            <tr>
                <td><c:out value="${car.id}"/></td>
                <td><c:out value="${car.km}"/></td>
                <td><c:out value="${car.price}"/></td>
                <td><c:out value="${car.model}"/></td>
                <td><c:out value="${car.color}"/></td>
                <td>
                    <a href="edit?id=<c:out value='${car.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${car.id}'/>">Delete car</a>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>

