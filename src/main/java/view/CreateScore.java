package view;

import entity.Player;
import entity.Score;
import logic.IllegalFormParameterException;
import logic.PlayerLogic;
import logic.ScoreLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet to handle the creation of score from a JSP form CreateScore.jsp
 * @author leon
 */
public class CreateScore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // make sure user clicked add button
        if (request.getParameter("add") != null) {
            ScoreLogic logic = new ScoreLogic();
            Score score = null;

            try {
                score = logic.createEntity(request.getParameterMap());

            } catch (IllegalFormParameterException e) {
                // input validation failed, set error messages and display
                request.setAttribute("errorMessages", e.getErrorMessages());
                doGet(request, response);
            }

            Player player = new PlayerLogic().getPlayerWithId(Integer.valueOf(request.getParameter(ScoreLogic.PLAYER_ID)));
            if (player == null) {
                // can not get the player with the input id
                Map<String, String> errorMessages = new HashMap<>();
                errorMessages.put("idError", "* can not find player with this id!");
                request.setAttribute("errorMessages", errorMessages);
                doGet(request, response);
            }

            score.setPlayerid(player);
            logic.add(score);
            response.sendRedirect("ViewScore");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/CreateScore.jsp").forward(request, response);
    }
}
