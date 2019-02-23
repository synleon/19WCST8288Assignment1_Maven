package view;

import entity.Player;
import entity.Score;
import logic.PlayerLogic;
import logic.ScoreLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateScore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            Map<String, String> errorMessages = new HashMap<>();
            String playeridInput = request.getParameter(ScoreLogic.PLAYER_ID);
            String scoreInput = request.getParameter(ScoreLogic.SCORE);
            if (playeridInput.isEmpty())
                errorMessages.put("idError", "* player id can not be empty!");
            if (scoreInput.isEmpty())
                errorMessages.put("scoreError", "* Score can not be empty!");

            if (errorMessages.isEmpty()) {
                ScoreLogic logic = new ScoreLogic();
                Score score = logic.createEntity(request.getParameterMap());
                Player player = new PlayerLogic().getPlayerWithId(Integer.valueOf(request.getParameter(ScoreLogic.PLAYER_ID)));
                score.setPlayerid(player);
                logic.add(score);
                request.getRequestDispatcher("/jsp/ScoresTableView.jsp").forward(request, response);
            }
            else {
                request.setAttribute("errorMessages", errorMessages);
                doGet(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/CreateScore.jsp").forward(request, response);
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
