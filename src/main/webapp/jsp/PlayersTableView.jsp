<%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-21
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Players</title>
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

        function createDateInput(text, name) {
            var node = document.createElement("input");
            node.name = name;
            node.className = "editor";
            node.type = "date";
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
                    var firstNameCell = document.getElementById(++id);
                    var lastNameCell = document.getElementById(++id);
                    var joinedCell = document.getElementById(++id);
                    var emailCell = document.getElementById(++id);
                    var idInput = createTextInput(idCell.innerText, "id");
                    idInput.readOnly = true;
                    var firstNameInput = createTextInput(firstNameCell.innerText, "firstName");
                    var lastNameInput = createTextInput(lastNameCell.innerText, "lastName");
                    var joinedInput = createDateInput(joinedCell.innerText, "joined");
                    var emailInput = createTextInput(emailCell.innerText, "email");

                    idCell.innerText = null;
                    firstNameCell.innerText = null;
                    lastNameCell.innerText = null;
                    joinedCell.innerText = null;
                    emailCell.innerText = null;

                    idCell.appendChild(idInput);
                    firstNameCell.appendChild(firstNameInput);
                    lastNameCell.appendChild(lastNameInput);
                    joinedCell.appendChild(joinedInput);
                    emailCell.appendChild(emailInput);
                };
            }
        };
    </script>
</head>
<body>
<form method="post" action="PlayerAction">

    <table align="center">
        <tr>
            <td><input type="text" name="searchText"/></td>
            <td><input type="submit" name="search" value="Search"/></td>
        </tr>
    </table>
    <h3>List of players</h3><br>
    <table align="center" border="1">
        <tr>
            <th><input type="submit" name="delete" value="Delete"/></th>
            <th>Edit</th>
            <th>id</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>Joined</th>
            <th>email</th>
        </tr>
        <c:forEach var="entity" items="${entities}" varStatus="loop">
            <tr>
                <td class="delete"><input type="checkbox" name="deleteMark" value="${entity.id}"></td>
                <td class="edit" id="${loop.index * 6}"><input class="update" type="button" name="edit" value="Edit"></td>
                <td class="code" id="${loop.index * 6 + 1}">${entity.id}</td>
                <td class="name" id="${loop.index * 6 + 2}">${entity.firstName}</td>
                <td class="name" id="${loop.index * 6 + 3}">${entity.lastName}</td>
                <td class="name" id="${loop.index * 6 + 4}">${entity.joined}</td>
                <td class="name" id="${loop.index * 6 + 5}">${entity.email}</td>
            </tr>
        </c:forEach>
        <tr>
            <th><input type="submit" name="delete" value="Delete"/></th>
            <th>Edit</th>
            <th>id</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>Joined</th>
            <th>email</th>
        </tr>
    </table>
</form>
</body>
</html>
