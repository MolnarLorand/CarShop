<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        Add new car form
    </title>
</head>

<style>
    body{
        background-color: azure;
    }
</style>

<body>
<h1 align="center">Add new car inside the data base</h1>
<div align="center">
    <a href="new">add new car </a>
    &nbsp;&nbsp;&nbsp;
    <a href="list">list all cars</a>
</div>
<br>
<div align="center">
    <c:if test="${car != null}">
    <form action="update" method="post">
        </c:if>

        <c:if test="${car == null}">
        <form action="insert" method="post" >
            </c:if>
            <table border="2" cellpadding="5">
                <c:if test="${car != null}">
                    <caption>Update</caption>
                </c:if>
                <c:if test="${car == null}">
                    <caption>New</caption>
                </c:if>

                <c:if test="${car != null}">
                    <input type="hidden" name="id" value="<c:out value='${car.id}'/>">
                </c:if>

                <tr>
                    <th>Model</th>
                    <td>
                        <input type="text" name="model" value="<c:out value='${car.model}'/>"/>
                    </td>
                </tr>

                <tr>
                    <th>Color</th>
                    <td>
                        <input type="text" name="color" value="<c:out value='${car.color}'/>"/>
                    </td>
                </tr>

                <tr>
                    <th>Price</th>
                    <td>
                        <input type="text" name="price" value="<c:out value='${car.price}'/>"/>
                    </td>
                </tr>

                <tr>
                    <th>Km</th>
                    <td>
                        <input type="text" name="km" value="<c:out value='${car.km}'/>"/>
                    </td>
                </tr>

                <tr>
                    <td align="center">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>


</div>
</body>
</html>