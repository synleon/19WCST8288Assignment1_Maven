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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableAction extends HttpServlet {

    /**
     * Delete usernames according to user's selection
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteUsernames(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsernameLogic logic = new UsernameLogic();
        String[] values = request.getParameterValues("deleteMark");
        if (values != null && values.length >= 1)
            for (String value: values)
                logic.deleteUsernameWithPlayerId(Integer.valueOf(value));
        viewUsernames(request, response);

    }

    /**
     * Delete Scores according to user's selection
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteScores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScoreLogic logic = new ScoreLogic();
        String[] values = request.getParameterValues("deleteMark");
        if (values != null && values.length >= 1)
            for (String value: values)
                logic.deleteScoreWithId(Integer.valueOf(value));
        viewScores(request, response);
    }

    /**
     * Delete players according to user's selection
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void deletePlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlayerLogic logic = new PlayerLogic();
        String[] values = request.getParameterValues("deleteMark");
        if (values != null && values.length >= 1)
            for (String value: values)
                logic.deletePlayerWithID(Integer.valueOf(value));
        viewPlayers(request, response);
    }

    /**
     * Update single record of username
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateUsername(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        UsernameLogic logic = new UsernameLogic();
        logic.updateUsernameWithPlayerId(parameterMap);
        viewUsernames(request, response);
    }

    /**
     * Update single record of score
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateScore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        ScoreLogic logic = new ScoreLogic();

        logic.updateScoreWithID(parameterMap);

        viewScores(request, response);
    }

    /**
     * Update single record of player
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void updatePlayer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        PlayerLogic logic = new PlayerLogic();
        logic.updatePlayerWithID(parameterMap);
        viewPlayers(request, response);
    }

    protected void searchUsernames(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: add search Username code here
    }
    protected void searchScores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: add search Score code here
    }

    protected void searchPlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: add search Player code here
    }

    /**
     * View players, redirect to corresponding JSP file after set entities as attribute
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void viewPlayers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlayerLogic logic = new PlayerLogic();
        List<Player> entities = logic.getAllPlayers();
        request.setAttribute("entities", entities);
        request.getRequestDispatcher("/jsp/PlayersTableView.jsp").forward(request, response);
    }

    /**
     * View usernames, redirect to corresponding JSP file after set entities as attribute
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void viewUsernames(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsernameLogic logic = new UsernameLogic();
        List<Username> entities = logic.getAll();
        request.setAttribute("entities", entities);
        request.getRequestDispatcher("/jsp/UsernameTableViewFancy.jsp").forward(request, response);
    }

    /**
     * View scores, redirect to corresponding JSP file after set entities as attribute
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void viewScores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScoreLogic logic = new ScoreLogic();
        List<Score> entities = logic.getAll();
        request.setAttribute("entities", entities);
        request.getRequestDispatcher("/jsp/ScoresTableView.jsp").forward(request, response);
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     * Edit, Delete of Username/PLayer/Score will be handled
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        String edit = request.getParameter("edit");
        String delete = request.getParameter("delete");
        String search = request.getParameter("search");
        if (url.matches(".*PlayerAction.*")) {
            if (delete != null && delete.trim().length() != 0)
                deletePlayers(request, response);
            if (edit != null && edit.trim().length() != 0)
                updatePlayer(request, response);
            if (search != null && search.trim().length() != 0)
                searchPlayers(request, response);
        }
        if (url.matches(".*UsernameAction.*")) {
            if (delete != null && delete.trim().length() != 0)
                deleteUsernames(request, response);
            if (edit != null && edit.trim().length() != 0)
                updateUsername(request, response);
            if (search != null && search.trim().length() != 0)
                searchUsernames(request, response);
        }
        if (url.matches(".*ScoreAction.*")) {
            if (delete != null && delete.trim().length() != 0)
                deleteScores(request, response);
            if (edit != null && edit.trim().length() != 0)
                updateScore(request, response);
            if (search != null && search.trim().length() != 0)
                searchScores(request, response);
        }

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

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    /**
     * Print parameters map
     * @param m parameters map from HttpServletRequest
     * @return strings representation of parameters map
     */
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
