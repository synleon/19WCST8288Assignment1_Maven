package view;

import entity.Player;
import entity.Username;
import logic.IllegalFormParameterException;
import logic.PlayerLogic;
import logic.UsernameLogic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreatePlayer extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            PlayerLogic playerLogic = new PlayerLogic();

            Player player = null;
            try {
                player = playerLogic.createEntity(request.getParameterMap());
            } catch (IllegalFormParameterException e) {
                request.setAttribute("playerErrMessages", e.getErrorMessages());
                doGet(request, response);
            }
            playerLogic.add(player);

            UsernameLogic usernameLogic = new UsernameLogic();
            Username username = null;
            try {
                username = usernameLogic.createEntity(request.getParameterMap());
            } catch (IllegalFormParameterException e) {
                request.setAttribute("usernameErrMessages", e.getErrorMessages());
                doGet(request, response);
            }
            username.setPlayer(player);
            usernameLogic.add(username);

            request.getRequestDispatcher("/jsp/PlayersTableView.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/CreatePlayer.jsp").forward(request, response);
    }

//    private String toStringMap(Map<String, String[]> m) {
//        StringBuilder builder = new StringBuilder();
//        for (String k : m.keySet()) {
//            builder.append("Key=").append(k)
//                    .append(", ")
//                    .append("Value/s=").append(Arrays.toString(m.get(k)))
//                    .append(System.lineSeparator());
//        }
//        return builder.toString();
//    }
}
