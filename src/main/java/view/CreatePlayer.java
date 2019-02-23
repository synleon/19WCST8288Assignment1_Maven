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
import java.util.HashMap;
import java.util.Map;

public class CreatePlayer extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/jsp/CreatePlayer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            PlayerLogic playerLogic = new PlayerLogic();
            // performing data validity check
            // id, firstname, lastname, username must be string and not equal null
            String id = request.getParameter(PlayerLogic.ID);
            String firstName = request.getParameter(PlayerLogic.FIRST_NAME);
            String lastName = request.getParameter(PlayerLogic.LAST_NAME);
            String email = request.getParameter(PlayerLogic.EMAIL);
            String userName = request.getParameter(UsernameLogic.USERNAME);

            Map<String, String> errorMessages = new HashMap<>();
            errorMessages.clear();

            if (id == null || id.trim().length() == 0) {
                errorMessages.put("idError", "*id can not be empty!");
            }

            if (firstName == null || firstName.trim().length() == 0) {
                errorMessages.put("firstNameError", "*firstName can not be empty!");
            }

            if (lastName == null || lastName.trim().length() == 0) {
                errorMessages.put("lastNameError", "*lastName can not be empty!");
            }

            if (userName == null || userName.trim().length() == 0) {
                errorMessages.put("userNameError", "*userName can not be empty!");
            }

            if (errorMessages.isEmpty())
            {
                Player player = playerLogic.createEntity(request.getParameterMap());
                playerLogic.add(player);

                UsernameLogic usernameLogic = new UsernameLogic();
                Username username = usernameLogic.createEntity(request.getParameterMap());
                username.setPlayer(player);
                usernameLogic.add(username);

                request.getRequestDispatcher("/jsp/PlayersTableView.jsp").forward(request, response);
            }
            else {
                request.setAttribute("errorMessages", errorMessages);
                doGet(request, response);
            }
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
