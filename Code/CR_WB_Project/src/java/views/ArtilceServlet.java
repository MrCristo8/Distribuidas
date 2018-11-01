/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CR_WB_Article;

/**
 *
 * @author wason
 */
@WebServlet(name = "ArtilceServlet", urlPatterns =
{
    "/ArtilceServlet"
})
public class ArtilceServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ArtilceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArtilceServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        persistance.ArticlePersistance.getInstnace().LoadArticles();
        String article_page = "<table>\n"
                + "        <tr>\n"
                + "            <th>Article ID</th>\n"
                + "            <th>Article Name</th>\n"
                + "            <th>Article Price</th>\n"
                + "            <th>Article Stock</th>\n"
                + "        </tr>";
        article_page = persistance.ArticlePersistance.getInstnace().getArticleList().stream().map((x)
                -> "<tr><td>" + x.getArticle_id() + "</td></tr>"
                + "<td>" + x.getArticle_name() + "</td>"
                + "<td>" + x.getArticle_price() + "</td>"
                + "<td>" + x.getArticle_stock() + "</td>").reduce(article_page, String::concat);
        article_page += "</table>";
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <title>Dashboard</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css\">\n"
                    + "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n"
                    + "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js\"></script>\n"
                    + "    <style type=\"text/css\">\n"
                    + "        .wrapper{\n"
                    + "            width: 650px;\n"
                    + "            margin: 0 auto;\n"
                    + "        }\n"
                    + "        .page-header h2{\n"
                    + "            margin-top: 0;\n"
                    + "        }\n"
                    + "        table tr td:last-child a{\n"
                    + "            margin-right: 15px;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "    <script type=\"text/javascript\">\n"
                    + "        $(document).ready(function(){\n"
                    + "            $('[data-toggle=\"tooltip\"]').tooltip();   \n"
                    + "        });\n"
                    + "    </script>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <div class=\"wrapper\">\n"
                    + "        <div class=\"container-fluid\">\n"
                    + "            <div class=\"row\">\n"
                    + "                <div class=\"col-md-12\">\n"
                    + "                    <div class=\"page-header clearfix\">");
            out.println(article_page);
            out.println("   </div>\n"
                    + "            </div>        \n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</body>\n"
                    + "</html>");
        }

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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
