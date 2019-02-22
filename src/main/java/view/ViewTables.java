package view;

import entity.Player;
import entity.Score;
import entity.Username;
import logic.PlayerLogic;
import logic.ScoreLogic;
import logic.UsernameLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ViewTables extends HttpServlet {

    protected void viewPlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/jsp/PlayersTableView.jsp").forward(request, response);
    }

    protected void DeletePlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlayerLogic logic = new PlayerLogic();
        String[] values = request.getParameterValues("deleteMark");
        if (values != null && values.length >= 1)
            for (String value: values)
                logic.deletePlayerWithID(Integer.valueOf(value));
        viewPlayers(request, response);
    }

    protected void UpdatePlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: add update player code here
        Map<String, String[]> parameterMap = request.getParameterMap();
        PlayerLogic logic = new PlayerLogic();
        logic.updatePlayerWithID(parameterMap);
        viewPlayers(request, response);
    }

    protected void SearchPlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: add search player code here
    }

    protected void viewUsernames(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Usernames</title>");
            applyStyleSheet(out);
            out.println("<script>");
            out.println("var isEditActive = false;");
            out.println("var activeEditID = -1;");
            out.println("function createTextInput(text, name) {");
            out.println("var node = document.createElement(\"input\");");
            out.println("node.name = name;");
            out.println("node.className = \"editor\";");
            out.println("node.type = \"text\";");
            out.println("node.value = text;");
            out.println("return node;");
            out.println("}");
            out.println("window.onload = function () {");
            out.println("var elements = document.getElementsByClassName(\"edit\");");
            out.println("for (let i = 0; i < elements.length; i++) {");
            out.println("elements[i].childNodes[0].onclick = function () {");
            out.println("var id = elements[i].id;");
            out.println("if (isEditActive) {");
            out.println("if (activeEditID === id) {");
            out.println("this.type = \"submit\";");
            out.println("}");
            out.println("return;");
            out.println("}");
            out.println("isEditActive = true;");
            out.println("activeEditID = id;");
            out.println("this.value = \"Update\";");
            out.println("var idCell = document.getElementById(++id);");
            out.println("var nameCell = document.getElementById(++id);");
            out.printf("var idInput = createTextInput(idCell.innerText, \"%s\");", UsernameLogic.PLAYER_ID);
            out.println("idInput.readOnly = true;");
            out.printf("var nameInput = createTextInput(nameCell.innerText, \"%s\");", UsernameLogic.USERNAME);
            out.println("idCell.innerText = null;");
            out.println("nameCell.innerText = null;");
            out.println("idCell.appendChild(idInput);");
            out.println("nameCell.appendChild(nameInput);");
            out.println("};");
            out.println("}");
            out.println("};");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            //https://www.w3schools.com/css/css_table.asp
            out.println("<form method=\"post\" action=\"UsernameAction\">");

            out.println("<table align=\"center\">");
            out.println("<tr>");
            out.println("<td><input type=\"text\" name=\"searchText\" /></td>");
            out.println("<td><input type=\"submit\" name=\"search\" value=\"Search\" /></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<h3>List of usernames</h3><br>");
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<tr>");
            out.println("<th><input type=\"submit\" name=\"delete\" value=\"Delete\" /></th>");
            out.println("<th>Edit</th>");
            out.println("<th>Player ID</th>");
            out.println("<th>Username</th>");
            out.println("</tr>");
            UsernameLogic logic = new UsernameLogic();
            List<Username> entities = logic.getAll();
            long counter = 0;
            for (Username entity : entities) {
                out.println("<tr>");
                out.println("<td class=\"delete\">");
                out.printf("<input type=\"checkbox\" name=\"deleteMark\" value=\"%d\" />", entity.getPlayerid());
                out.println("</td>");
                out.printf("<td class=\"edit\" id=\"%d\" ><input class=\"update\" type=\"button\" name=\"edit\" value=\"Edit\" /></td>", counter++);
                out.printf("<td class=\"code\" id=\"%d\" >%d</td>", counter++, entity.getPlayerid());
                out.printf("<td class=\"name\" id=\"%d\" >%s</td>", counter++, entity.getUsername());
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<th><input type=\"submit\" name=\"delete\" value=\"Delete\" /></th>");
            out.println("<th>Edit</th>");
            out.println("<th>Player ID</th>");
            out.println("<th>Username</th>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            // out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>", toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
        }

    }

    protected void viewScores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Scores</title>");
            //https://www.w3schools.com/css/css_table.asp
//            out.println("<style>");
//            out.println("table {border-collapse: collapse;width: auto;}");
//            out.println("th, td {text-align: left;padding: 8px;}");
//            out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
//            out.println("td.edit{width:65px;}");
//            out.println("td.name{width:350px;}");
//            out.println("td.code{width:150px;}");
//            out.println("td.delete{text-align: center;}");
//            out.println("input.editor{width: 100%;}");
//            out.println("input.update{width: 100%;}");
//            out.println("</style>");

            applyStyleSheet(out);

            out.println("<script>");
            out.println("var isEditActive = false;");
            out.println("var activeEditID = -1;");
            out.println("function createTextInput(text, name) {");
            out.println("var node = document.createElement(\"input\");");
            out.println("node.name = name;");
            out.println("node.className = \"editor\";");
            out.println("node.type = \"text\";");
            out.println("node.value = text;");
            out.println("return node;");
            out.println("}");
            out.println("window.onload = function () {");
            out.println("var elements = document.getElementsByClassName(\"edit\");");
            out.println("for (let i = 0; i < elements.length; i++) {");
            out.println("elements[i].childNodes[0].onclick = function () {");
            out.println("var id = elements[i].id;");
            out.println("if (isEditActive) {");
            out.println("if (activeEditID === id) {");
            out.println("this.type = \"submit\";");
            out.println("}");
            out.println("return;");
            out.println("}");
            out.println("isEditActive = true;");
            out.println("activeEditID = id;");
            out.println("this.value = \"Update\";");
            out.println("var idCell = document.getElementById(++id);");
            out.println("var nameCell = document.getElementById(++id);");
            out.printf("var idInput = createTextInput(idCell.innerText, \"%s\");", ScoreLogic.ID);
            out.println("idInput.readOnly = true;");
            out.printf("var nameInput = createTextInput(nameCell.innerText, \"%s\");", ScoreLogic.SCORE);
            out.println("idCell.innerText = null;");
            out.println("nameCell.innerText = null;");
            out.println("idCell.appendChild(idInput);");
            out.println("nameCell.appendChild(nameInput);");
            out.println("};");
            out.println("}");
            out.println("};");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            //https://www.w3schools.com/css/css_table.asp
            out.println("<form method=\"post\" action=\"ScoreAction\">");

            out.println("<table align=\"center\">");
            out.println("<tr>");
            out.println("<td><input type=\"text\" name=\"searchText\" /></td>");
            out.println("<td><input type=\"submit\" name=\"search\" value=\"Search\" /></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<h3>List of scores</h3><br>");
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<tr>");
            out.println("<th><input type=\"submit\" name=\"delete\" value=\"Delete\" /></th>");
            out.println("<th>Edit</th>");
            out.println("<th>id</th>");
            out.println("<th>Player_id</th>");
            out.println("<th>score</th>");
            out.println("<th>submission</th>");
            out.println("</tr>");
            ScoreLogic logic = new ScoreLogic();
            List<Score> entities = logic.getAll();
            long counter = 0;
            for (Score score : entities) {
                out.println("<tr>");
                out.println("<td class=\"delete\">");
                out.printf("<input type=\"checkbox\" name=\"deleteMark\" value=\"%d\" />", score.getId());
                out.println("</td>");
                out.printf("<td class=\"edit\" id=\"%d\" ><input class=\"update\" type=\"button\" name=\"edit\" value=\"Edit\" /></td>", counter++);
                out.printf("<td class=\"id\" id=\"%d\">%d</td>", counter++, score.getId());
                out.printf("<td class=\"Player_id\" id=\"%d\" >%s</td>", counter++, score.getPlayerid().getId());
                out.printf("<td class=\"score\" id=\"%d\" >%s</td>", counter++, score.getScore());
                out.printf("<td class=\"submission\" id=\"%d\" >%s</td>", counter++, score.getSubmission().toString());
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<th><input type=\"submit\" name=\"delete\" value=\"Delete\" /></th>");
            out.println("<th>Edit</th>");
            out.println("<th>id</th>");
            out.println("<th>Player_id</th>");
            out.println("<th>score</th>");
            out.println("<th>submission</th>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            // out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>", toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        String edit = request.getParameter("edit");
        String delete = request.getParameter("delete");
        String search = request.getParameter("search");
        if (url.matches(".*PlayerAction.*")) {
            if (delete != null && delete.trim().length() != 0)
                DeletePlayers(request, response);
            if (edit != null && edit.trim().length() != 0)
                UpdatePlayers(request, response);
            if (search != null && search.trim().length() != 0)
                SearchPlayers(request, response);
        }
        if (url.matches(".*UsernameAction.*")) {
            //processRequestViewUsernames(request, response);
            log("DeleteUsernames");
        }
        if (url.matches(".*ScoreAction.*")) {
            //processRequestViewScores(request, response);
            log("DeleteScores");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        if (url.matches(".*ViewPlayer.*")) {
            viewPlayers(request, response);
        }
        if (url.matches(".*ViewUsername.*")) {
            viewUsernames(request, response);
        }
        if (url.matches(".*ViewScore.*")) {
            viewScores(request, response);
        }
    }

    protected void applyStyleSheet(PrintWriter out) {
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css\\viewtable.css\">");
    }
}
