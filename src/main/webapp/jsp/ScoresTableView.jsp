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
    <title>Title</title>
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
            node.type = "datetime-local";
            node.value = text.substring(0, 10) + 'T' + text.substring(11);
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
                    var playerIdCell = document.getElementById(++id);
                    var scoreCell = document.getElementById(++id);
                    var submissionCell = document.getElementById(++id);

                    var idInput = createTextInput(idCell.innerText, "scoreid");
                    var playerIdInput = createTextInput(playerIdCell.innerText, "id");
                    var scoreInput = createTextInput(scoreCell.innerText, "score");
                    var submissionInput = createDateInput(submissionCell.innerText, "submission");
                    idInput.readOnly = true;
                    playerIdInput.readOnly = true;
                    submissionInput.readOnly = true;

                    idCell.innerText = null;
                    playerIdCell.innerText = null;
                    scoreCell.innerText = null;
                    submissionCell.innerText = null;

                    idCell.appendChild(idInput);
                    playerIdCell.appendChild(playerIdInput);
                    scoreCell.appendChild(scoreInput);
                    submissionCell.appendChild(submissionInput);
                };
            }
        };
    </script>
</head>
<body>
<form method="post" action="ScoreAction">

    <table align="center">
        <tr>
            <td><input type="text" name="searchText"/></td>
            <td><input type="submit" name="search" value="Search"/></td>
        </tr>
    </table>
    <h3>List of scores</h3><br>
    <table align="center" border="1">
        <tr>
            <th><input type="submit" name="delete" value="Delete"/></th>
            <th>Edit</th>
            <th>id</th>
            <th>Player_id</th>
            <th>score</th>
            <th>submission</th>
        </tr>
        <c:forEach var="entity" items="${entities}" varStatus="loop">
            <tr>
                <td class="delete"><input type="checkbox" name="deleteMark" value="${entity.id}"></td>
                <td class="edit" id="${loop.index * 5}"><input class="update" type="button" name="edit" value="Edit"></td>
                <td class="code" id="${loop.index * 5 + 1}">${entity.id}</td>
                <td class="code" id="${loop.index * 5 + 2}">${entity.playerid.id}</td>
                <td class="name" id="${loop.index * 5 + 3}">${entity.score}</td>
                <td class="name" id="${loop.index * 5 + 4}">${entity.submission}</td>
            </tr>
        </c:forEach>
        <tr>
            <th><input type="submit" name="delete" value="Delete"></th>
            <th>Edit</th>
            <th>id</th>
            <th>Player_id</th>
            <th>score</th>
            <th>submission</th>
        </tr>
    </table>
</form>
</body>
</html>
