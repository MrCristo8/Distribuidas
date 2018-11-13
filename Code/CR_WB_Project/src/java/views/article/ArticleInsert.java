/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CR_WB_Article;
import model.CR_WB_Movement;
import oracle.jdbc.internal.OracleStatement;
import persistance.ArticlePersistance;
import persistance.MovementPersistance;

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
        Integer id = 1;        
        Date date = java.sql.Date.from(Instant.now());
        for (CR_WB_Article article : ArticlePersistance.getInstnace().getObjectList()) {
            if (!Objects.equals(article.getArticle_id(), id)) {
                continue;
            }
            id++;
        }
        CR_WB_Article inserted_record = new CR_WB_Article(
                id,
                request.getParameter("name"),
                Float.parseFloat(request.getParameter("price")),
                Integer.parseInt(request.getParameter("stock")),
                "CREATED");
        persistance.ArticlePersistance.getInstnace().getObjectList().add(inserted_record);        
        Integer movementId = 1;
        for (CR_WB_Movement movement_loop : MovementPersistance.getInstnace().getObjectList()) {
            if (!Objects.equals(movement_loop.getMovement_id(), movementId)) {
                continue;
            }
            movementId++;
        }
        CR_WB_Movement movement = new CR_WB_Movement(
                movementId,
                id,
                "INGRESO",
                date,
                inserted_record.getArticle_stock(),
                "+"
        );
        movement.setArticle(inserted_record);
        movement.setState("CREATED");
        persistance.MovementPersistance.getInstnace().getObjectList().add(movement);
        response.sendRedirect("/CR_WB_Project/ArticleServlet");
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
