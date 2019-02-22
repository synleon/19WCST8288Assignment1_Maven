<%@ page import="logic.ScoreLogic" %><%--
  Created by IntelliJ IDEA.
  User: synle
  Date: 2019-02-21
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/viewtable.css">
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
                    var idInput = createTextInput(idCell.innerText, "<%=ScoreLogic.PLAYER_ID%>");
                    idInput.readOnly = true;
                    var nameInput = createTextInput(nameCell.innerText, "<%=ScoreLogic.SCORE%>");
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

</body>
</html>
