package view;

import entity.Player;
import entity.Score;
import javafx.print.PageLayout;
import logic.PlayerLogic;
import logic.ScoreLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

public class CreateScore extends HttpServlet {
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
            out.println("<h2>Create a Score</h2>");
            out.println("<form action=\"CreateScore\" method=\"post\">");
            out.println("Player id:<br>");
            out.println("<input type=\"number\" name=\"id\"><br><br>");
            out.println("Score:<br>");
            out.println("<input type=\"number\" name=\"score\" value=\"Mickey\"><br><br>");
            out.println("submission on:<br>");
            out.println("<input type=\"date\" name=\"submission\" min=\"1900-01-01\"><br><br>");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            ScoreLogic logic = new ScoreLogic();
            Score score = logic.createEntity(request.getParameterMap());
            Player player = new PlayerLogic().getPlayerWithId(Integer.valueOf(request.getParameterMap().get(ScoreLogic.PLAYER_ID)[0]));
            Player player2 = new PlayerLogic().getPlayerWithId(Integer.valueOf(request.getParameter(ScoreLogic.PLAYER_ID)));
            score.setPlayerid(player);
            logic.add(score);
            request.getRequestDispatcher("/jsp/ScoresTableView.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
