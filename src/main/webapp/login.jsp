<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>CarShop Website</title>
</head>
<style>
    body{
        background-color: azure;
    }
    body{
        background-image: url('https://images.unsplash.com/photo-1551522435-a13afa10f103?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80');
        background-repeat: no-repeat;
        color: darkblue;
    }
    div{

        font-family: "Adobe Devanagari";
    }
</style>
<body>
    <div>
        <h1>
            Login to enjoy it.
        </h1>
        <form action="login" method="post">
            <label for="user_email">Email:</label>
            <input name="user_email" size="50"/>
            <br><br>
            <label for="password">Password:</label>
            <input name="user_password" size="50" type="password"/>
            <br>
            ${jspMessage}      <%-- ${} tag pentru valori din java --%>
            <br>
            <br>
            <button type="submit" style="font-family: 'Adobe Devanagari'" >Login</button>
        </form>
    </div>
</body>
</html>