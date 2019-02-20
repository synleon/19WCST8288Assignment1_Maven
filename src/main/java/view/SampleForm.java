package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;


// @WebServlet(name = "SampleForm", urlPatterns = "/SampleForm")
public class SampleForm extends HttpServlet {
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
            out.println("<title>Servlet Sample3Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: left;\">");
            out.println("<form action=\"SampleForm\" method=\"post\">");
            out.println("First name:<br>");
            out.println("<input type=\"text\" name=\"firstname\" value=\"Mickey\"><br>");
            out.println("Last name:<br>");
            out.println("<input type=\"text\" name=\"lastname\" value=\"Mouse\"><br><br>");
            out.println("<input type=\"radio\" name=\"gender\" value=\"male\" checked> Male<br>");
            out.println("<input type=\"radio\" name=\"gender\" value=\"female\"> Female<br>");
            out.println("<input type=\"radio\" name=\"gender\" value=\"other\"> Other<br><br>");
            out.println("<input type=\"checkbox\" name=\"car\" value=\"car\"> Car<br>");
            out.println("<input type=\"checkbox\" name=\"fly\" value=\"alien\"> Fly<br><br>");
            out.println("<input type=\"date\" name=\"bday\" min=\"1900-01-01\" max=\"2007-12-30\"><br><br>");
            out.println("<input type=\"submit\" value=\"Submit\">");
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

    private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params =  request.getParameterMap();
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
