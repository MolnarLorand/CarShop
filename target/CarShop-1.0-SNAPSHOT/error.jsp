<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h1>
        Error
    </h1>
    <br>
<h2>
    <%=exception.getMessage() %>
</h2>
</body>
</html>