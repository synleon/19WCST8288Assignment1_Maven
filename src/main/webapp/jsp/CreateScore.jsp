<%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-23
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create a score</title>
    <style> .error {color: #FF0000;} </style>
</head>
<body>
<div style="text-align: center;">
    <div style="display: inline-block; text-align: left;">
        <h2>Create a Player</h2>
        <form action="CreateScore" method="post">
            Player id:<br>
            <input type="number" name="id"><span class="error">${errorMessages.idError}</span><br><br>
            Score:<br>
            <input type="number" name="score"><span class="error">${errorMessages.scoreError}</span><br><br>
            <input type="submit" name="add" value="Add and View">
        </form>
    </div>
</div>
</body>
</html>
