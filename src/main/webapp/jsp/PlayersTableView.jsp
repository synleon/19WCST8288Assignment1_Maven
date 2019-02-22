<%@ page import="logic.PlayerLogic" %>
<%@ page import="entity.Player" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-21
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    var idInput = createTextInput(idCell.innerText, "<%=PlayerLogic.ID%>");
                    idInput.readOnly = true;
                    var firstNameInput = createTextInput(firstNameCell.innerText, "<%=PlayerLogic.FIRST_NAME%>");
                    var lastNameInput = createTextInput(lastNameCell.innerText, "<%=PlayerLogic.LAST_NAME%>");
                    var joinedInput = createDateInput(joinedCell.innerText, "<%=PlayerLogic.JOINED%>");
                    var emailInput = createTextInput(emailCell.innerText, "<%=PlayerLogic.EMAIL%>");

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
        <%
            PlayerLogic logic = new PlayerLogic();
            List<Player> entities = logic.getAllPlayers();
            long counter = 0;
            for (Player entity : entities) {
        %>
        <tr>
            <td class="delete"><input type="checkbox" name="deleteMark" value="<%=entity.getId()%>"></td>
            <td class="edit" id="<%=counter++%>"><input class="update" type="button" name="edit" value="Edit"></td>
            <td class="code" id="<%=counter++%>"><%=entity.getId().toString()%></td>
            <td class="name" id="<%=counter++%>"><%=entity.getFirstName()%></td>
            <td class="name" id="<%=counter++%>"><%=entity.getLastName()%></td>
            <td class="name" id="<%=counter++%>"><%=entity.getJoined().toString()%></td>
            <td class="name" id="<%=counter++%>"><%=entity.getEmail()%></td>
        </tr>
        <%
            }
        %>
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
