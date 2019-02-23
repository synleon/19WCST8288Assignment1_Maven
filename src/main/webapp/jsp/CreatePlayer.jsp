<%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-22
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create a player</title>
    <style> .error {color: #FF0000;} </style>
</head>
<body>
<div style="text-align: center;">
    <div style="display: inline-block; text-align: left;">
        <h2>Create a Player</h2>
        <form action="CreatePlayer" method="post">
            Player id:<br>
            <input type="number" name="id"><span class="error">${errorMessages.idError}</span><br>
            First name:<br>
            <input type="text" name="firstName"><span class="error">${errorMessages.firstNameError}</span><br>
            Last name:<br>
            <input type="text" name="lastName"><span class="error">${errorMessages.lastNameError}</span><br>
            Email:<br>
            <input type="email" name="email"><br>
            Username:<br>
            <input type="text" name="username"><span class="error">${errorMessages.userNameError}</span><br><br>
            <input type="submit" name="add" value="Add and View">
        </form>
        <%--<pre><%=toStringMap(request.getParameterMap())%></pre>--%>
    </div>
</div>
</body>
</html>
<%--<%!--%>
    <%--String toStringMap(Map<String, String[]> m) {--%>
    <%--StringBuilder builder = new StringBuilder();--%>
    <%--for (String k : m.keySet()) {--%>
    <%--builder.append("Key=").append(k)--%>
    <%--.append(", ")--%>
    <%--.append("Value/s=").append(Arrays.toString(m.get(k)))--%>
    <%--.append(System.lineSeparator());--%>
    <%--}--%>
    <%--return builder.toString();--%>
    <%--}--%>
<%--%>--%>