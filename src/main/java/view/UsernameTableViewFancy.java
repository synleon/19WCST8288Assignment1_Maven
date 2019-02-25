package view;


import entity.Username;
import logic.UsernameLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsernameTableViewFancy extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sample2Servlet</title>");
            //https://www.w3schools.com/css/css_table.asp
            out.println("<style>");
            out.println("table {border-collapse: collapse;width: auto;}");
            out.println("th, td {text-align: left;padding: 8px;}");
            out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
            out.println("td.edit{width:65px;}");
            out.println("td.name{width:350px;}");
            out.println("td.code{width:150px;}");
            out.println("td.delete{text-align: center;}");
            out.println("input.editor{width: 100%;}");
            out.println("input.update{width: 100%;}");
            out.println("</style>");
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
            out.println("<form method=\"post\">");

            out.println("<table align=\"center\">");
            out.println("<tr>");
            out.println("<td><input type=\"text\" name=\"searchText\" /></td>");
            out.println("<td><input type=\"submit\" name=\"search\" value=\"Search\" /></td>");
            out.println("</tr>");
            out.println("</table>");
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
            out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>", toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
        }
    }

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

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        request.getRequestDispatcher("ViewUsernames").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsernameLogic logic = new UsernameLogic();
        String[] values = request.getParameterValues("deleteMark");
        //if(values!=null && values.length>=1)
        //logic.deleteCourses( values);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
