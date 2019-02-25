package view;

import entity.Player;
import entity.Username;
import logic.IllegalFormParameterException;
import logic.PlayerLogic;
import logic.UsernameLogic;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to handle player create form CreatePlayer.jsp
 * @author leon
 */
public class CreatePlayer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // make sure user clicked add button
        if (request.getParameter("add") != null) {
            PlayerLogic playerLogic = new PlayerLogic();

            Player player = null;
            try {
                player = playerLogic.createEntity(request.getParameterMap());
            } catch (IllegalFormParameterException e) {
                // user input validation failed, set error messages and display
                request.setAttribute("playerErrMessages", e.getErrorMessages());
                doGet(request, response);
            }
            playerLogic.add(player);

            UsernameLogic usernameLogic = new UsernameLogic();
            Username username = null;
            try {
                username = usernameLogic.createEntity(request.getParameterMap());
            } catch (IllegalFormParameterException e) {
                // user input validation failed, set error messages and display
                request.setAttribute("usernameErrMessages", e.getErrorMessages());
                doGet(request, response);
            }
            username.setPlayer(player);
            usernameLogic.add(username);

            // after a successful creation, redirect user to player viewing page
            response.sendRedirect("ViewPlayer");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/CreatePlayer.jsp").forward(request, response);
    }
}
