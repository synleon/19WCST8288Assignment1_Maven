<%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-05
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="entity.Username"%>
<%@page import="logic.UsernameLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Servlet Sample2Servlet</title>
    <!-- https://www.w3schools.com/css/css_table.asp -->
    <style>
        table {
            border-collapse: collapse;
            width: auto;
        }
        th,
        td {
            text-align: left;
            padding: 8px;
        }
        td.edit{
            width:65px;
        }
        td.name{
            width:350px;
        }
        td.code{
            width:150px;
        }
        td.delete{
            text-align: center;
        }
        input.editor{
            width: 100%;
        }
        input.update{
            width: 100%;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
    <script>
        var isEditActive = false;
        var activeEditID = -1;
        function createTextInput(text, name) {
            var node = document.createElement("input");
            node.name = name;
            node.className = "editor";
            node.type = "text";
            node.value = text;
            return node;
        }
        window.onload = function () {
            var elements = document.getElementsByClassName("edit");
            for (let i = 0; i < elements.length; i++) {
                elements[i].childNodes[0].onclick = function () {
                    var id = elements[i].id;
                    if (isEditActive) {
                        if (activeEditID === id) {
                            this.type = "submit";
                        }
                        return;
                    }
                    isEditActive = true;
                    activeEditID = id;
                    this.value = "Update";
                    var idCell = document.getElementById(++id);
                    var nameCell = document.getElementById(++id);
                    var idInput = createTextInput(idCell.innerText, "<%=UsernameLogic.PLAYER_ID%>");
                    idInput.readOnly = true;
                    var nameInput = createTextInput(nameCell.innerText, "<%=UsernameLogic.USERNAME%>");
                    idCell.innerText = null;
                    nameCell.innerText = null;
                    idCell.appendChild(idInput);
                    nameCell.appendChild(nameInput);
                };
            }
        };
    </script>
</head>
<body>
<!-- https://www.w3schools.com/css/css_table.asp -->
<form method="post">
    <table align="center">
        <tr>
            <td><input type="text" name="searchText" /></td>
            <td><input type="submit" name="search" value="Search" /></td>
        </tr>
    </table>

    <table align="center" border="1">
        <tr>
            <th><input type="submit" name="delete" value="Delete" /></th>
            <th>Edit</th>
            <th>Player ID</th>
            <th>Username</th>
        </tr>
        <%
            UsernameLogic logic = new UsernameLogic();
            List<Username> usernames = logic.getAll();
            long counter = 0;
            for (Username username : usernames) {
        %>
        <tr>
            <td class="delete">
                <input type="checkbox" name="deleteMark" value="<%=username.getPlayerid()%>" />
            </td>
            <td class="edit" id="<%=counter++%>" ><input class="update" type="button" name="edit" value="Edit" /></td>
            <td class="name" id="<%=counter++%>" ><%=username.getPlayerid()%></td>
            <td class="name" id="<%=counter++%>" ><%=username.getUsername()%></td>
        </tr>
        <%
            }
        %>
        <tr>
            <th><input type="submit" name="delete" value="Delete" /></th>
            <th>Edit</th>
            <th>Player ID</th>
            <th>Username</th>
        </tr>
    </table>
</form>
<div style="text-align: center;"><pre><%=toStringMap(request.getParameterMap())%></pre></div>
</body>
</html>
<%!
    private String toStringMap(Map<String, String[]> m) {
        StringBuilder builder = new StringBuilder();
        for (String k : m.keySet()) {
            builder.append("Key=").append(k)
                    .append(", ")
                    .append("Value/s=").append(Arrays.toString(m.get(k)))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
%>
