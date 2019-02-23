<%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-05
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Servlet Sample2Servlet</title>
    <!-- https://www.w3schools.com/css/css_table.asp -->
    <link rel="stylesheet" type="text/css" href="css\viewtable.css">
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
                    var idInput = createTextInput(idCell.innerText, "id");
                    idInput.readOnly = true;
                    var nameInput = createTextInput(nameCell.innerText, "username");
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
<form method="post" action="UsernameAction">
    <table align="center">
        <tr>
            <td><input type="text" name="searchText"/></td>
            <td><input type="submit" name="search" value="Search"/></td>
        </tr>
    </table>
    <h3>List of Usernames</h3><br>
    <table align="center" border="1">
        <tr>
            <th><input type="submit" name="delete" value="Delete"/></th>
            <th>Edit</th>
            <th>Player ID</th>
            <th>Username</th>
        </tr>

        <c:forEach var="entity" items="${entities}" varStatus="loop">
            <tr>
                <td class="delete"><input type="checkbox" name="deleteMark" value="${entity.playerid}"/></td>
                <td class="edit" id="${loop.index * 3}"><input class="update" type="button" name="edit" value="Edit"/></td>
                <td class="name" id="${loop.index * 3 + 1}">${entity.playerid}</td>
                <td class="name" id="${loop.index * 3 + 2}">${entity.username}</td>
            </tr>
        </c:forEach>

        <tr>
            <th><input type="submit" name="delete" value="Delete"/></th>
            <th>Edit</th>
            <th>Player ID</th>
            <th>Username</th>
        </tr>
    </table>
</form>
</body>
</html>
