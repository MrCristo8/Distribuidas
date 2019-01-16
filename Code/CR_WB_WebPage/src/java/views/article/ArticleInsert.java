/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_ARTICLE;
import persistance.ArticlePersistance;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "ArticleInsert", urlPatterns = {"/ArticleInsert"})
public class ArticleInsert extends HttpServlet {

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
            out.println("<title>Servlet ArticleInsert</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArticleInsert at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        Integer id = 1;
        if (!request.getParameter("name").equals("") && !request.getParameter("price").equals("") && !request.getParameter("stock").equals("")) {
            try {
                for (WB_CR_ARTICLE  article : ArticlePersistance.getInstance().getObjectList()) {
                    if (!Objects.equals(article.getArticle_id(), id)) {
                        continue;
                    }
                    id++;
                }
                int stock = Integer.parseInt(request.getParameter("stock"));
                if (stock >= 0) {
                    WB_CR_ARTICLE inserted_record = new WB_CR_ARTICLE (
                            id,
                            request.getParameter("name"),
                            Float.parseFloat(request.getParameter("price")),
                            stock,
                            "CREATED");
                    persistance.ArticlePersistance.getInstance().getObjectList().add(inserted_record);
                    response.sendRedirect("/CR_WB_Project/ArticleServlet");
                }else {
                    response.setContentType("text/html");
                    out.println("<script> alert('No se puede ingresar un sock negativo'); </script>");
                    response.sendRedirect("/CR_WB_Project/ArticleServlet");
                }
            } catch (IOException | NumberFormatException e) {
                response.setContentType("text/html");
                out.println("<script> alert(" + e.getMessage() + "); </script>");
                response.sendRedirect("/CR_WB_Project/ArticleServlet");
            }
        } else {
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar datos antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_Project/ArticleServlet");
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
