/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.TempArrays;
import model.WB_CR_USER;
import querys.WB_CR_ARTICLE_MOVEMENT;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "ArticleByMovement", urlPatterns = {"/ArticleByMovement"})
public class ArticleByMovement extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ArticleByMovement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArticleByMovement at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException {
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/reports/articleByMovement.jsp");
        ArrayList<String> articles = new ArrayList<>();
        ArrayList<String> movements = new ArrayList<>();
        for (WB_CR_ARTICLE_MOVEMENT wb_cr_article_movement : reports.QueyPersistance.getInstance().getArticleByMovement()) {
            if (movements.isEmpty()) {
                movements.add(wb_cr_article_movement.getMovement_name());
            } else {
                int i;
                for (i = 0; i < movements.size(); i++) {
                    if (movements.get(i).equals(wb_cr_article_movement.getMovement_name())) {
                        break;
                    }
                }
                if (i >= movements.size()) {
                    movements.add(wb_cr_article_movement.getMovement_name());
                }
            }
        }
        for (WB_CR_ARTICLE_MOVEMENT wb_cr_article_movement : reports.QueyPersistance.getInstance().getArticleByMovement()) {
            if (articles.isEmpty()) {
                articles.add(wb_cr_article_movement.getArticle_name());
            } else {
                int i;
                for (i = 0; i < articles.size(); i++) {
                    if (articles.get(i).equals(wb_cr_article_movement.getArticle_name())) {
                        break;
                    }
                }
                if (i >= articles.size()) {
                    articles.add(wb_cr_article_movement.getArticle_name());
                }
            }
        }
        request.setAttribute("articles", articles);
        request.setAttribute("movements", movements);
        request.setAttribute("objList", reports.QueyPersistance.getInstance().getArticleByMovement());
        if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER())) {
            String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
            Arrays.sort(permission);
            request.setAttribute("permission", permission);
        } else {
            request.setAttribute("permission", new String[]{});
        }
        if (dispatcher != null) {
            dispatcher.forward(request, response);
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
            throws ServletException, IOException {
        if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER())) {
            String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
            Arrays.sort(permission);
            request.setAttribute("permission", permission);
        } else {
            request.setAttribute("permission", new String[]{});
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
