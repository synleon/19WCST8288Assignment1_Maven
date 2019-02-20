package view;

import entity.Player;
import entity.Username;
import logic.PlayerLogic;
import logic.UsernameLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

public class CreatePlayer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create a player</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: left;\">");
            out.println("<h2>Create a Player</h2>");
            out.println("<form action=\"CreatePlayer\" method=\"post\">");
            out.println("Player id:<br>");
            out.println("<input type=\"number\" name=\"id\"><br><br>");
            out.println("First name:<br>");
            out.println("<input type=\"text\" name=\"firstName\" value=\"Mickey\"><br><br>");
            out.println("Last name:<br>");
            out.println("<input type=\"text\" name=\"lastName\" value=\"Mouse\"><br><br>");
            out.println("Email:<br>");
            out.println("<input type=\"email\" name=\"email\"><br><br>");
            out.println("Username:<br>");
            out.println("<input type=\"text\" name=\"username\"><br><br>");
            out.println("<input type=\"submit\" name=\"add\" value=\"Add and View\">");
            out.println("</form>");
            out.println("<pre>");
            out.println("Submitted keys and values:");
            out.println(toStringMap(request.getParameterMap()));
            out.println("</pre>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            PlayerLogic playerLogic = new PlayerLogic();
            Player player = playerLogic.createEntity(request.getParameterMap());
            playerLogic.add(player);

            UsernameLogic usernameLogic = new UsernameLogic();
            Username username = usernameLogic.createEntity(request.getParameterMap());
            username.setPlayer(player);
            usernameLogic.add(username);

            request.getRequestDispatcher("PlayerTableViewFancy").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
}
