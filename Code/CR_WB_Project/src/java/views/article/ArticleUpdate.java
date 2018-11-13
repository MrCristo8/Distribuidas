/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CR_WB_Article;
import model.CR_WB_Movement;
import persistance.MovementPersistance;

/**
 *
 * @author wason
 */
@WebServlet(name = "ArticleUpdate", urlPatterns
        = {
            "/ArticleUpdate"
        })
public class ArticleUpdate extends HttpServlet {

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
            out.println("<title>Servlet ArticleUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArticleUpdate at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("article_id"));
        int pos = persistance.ArticlePersistance.getInstnace().getObjectList().indexOf(new CR_WB_Article(id));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/WEB-INF/article/articleupdate.jsp");
        request.setAttribute("article", persistance.ArticlePersistance.getInstnace().getObjectList().get(pos));
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
        /*this should go into the logic package*/
        /*need to alter movement table, to register changes in stock*/
        PrintWriter out = response.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        if (!request.getParameter("name").equals("") && !request.getParameter("price").equals("") && !request.getParameter("stock").equals("")) {
            try {
                String state = "UPDATED";
                String movementName;
                int movementAmmount;
                String movementDirection;
                Date date = new Date();
                int newStock = Integer.parseInt(request.getParameter("stock"));
                if (newStock >= 0) {
                    int pos = persistance.ArticlePersistance.getInstnace().
                            getObjectList().indexOf(new CR_WB_Article(id));
                    int stock = persistance.ArticlePersistance.getInstnace().getObjectList().get(pos).getArticle_stock();
                    if (persistance.ArticlePersistance.getInstnace().getObjectList().get(pos).getState().equals("CREATED")) {
                        state = "CREATED";
                    }
                    CR_WB_Article updated_record = new CR_WB_Article(
                            id,
                            request.getParameter("name"),
                            Float.parseFloat(request.getParameter("price")),
                            newStock,
                            state);

                    if (updated_record.getArticle_stock() > stock) {
                        movementName = "INGRESO";
                        movementAmmount = updated_record.getArticle_stock() - stock;
                        movementDirection = "+";
                    } else {
                        movementName = "EGRESO";
                        movementAmmount = stock - updated_record.getArticle_stock();
                        movementDirection = "-";
                    }
                    persistance.ArticlePersistance.getInstnace().
                            getObjectList().remove(pos);
                    persistance.ArticlePersistance.getInstnace().
                            getObjectList().add(pos, updated_record);
                    //Save Movement
                    Integer movementId = 1;
                    for (CR_WB_Movement movement : MovementPersistance.getInstnace().getObjectList()) {
                        if (!Objects.equals(movement.getMovement_id(), movementId)) {
                            continue;
                        }
                        movementId++;
                    }
                    CR_WB_Movement movement = new CR_WB_Movement(
                            movementId,
                            id,
                            movementName,
                            date,
                            movementAmmount,
                            movementDirection
                    );
                    movement.setArticle(updated_record);
                    movement.setState("CREATED");
                    persistance.MovementPersistance.getInstnace().getObjectList().add(movement);
                    response.sendRedirect("/CR_WB_Project/ArticleServlet");
                } else {
                    response.setContentType("text/html");
                    out.println("<script> alert('No se puede ingresar un sock negativo'); </script>");
                    response.sendRedirect("/CR_WB_Project/ArticleUpdate?article_id="+id);
                }
            } catch (NumberFormatException e) {
                response.setContentType("text/html");
                out.println("<script> alert(" + e.getMessage() + "); </script>");
                response.sendRedirect("/CR_WB_Project/ArticleUpdate?article_id="+id);
            }
        } else {
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar datos antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_Project/ArticleUpdate?article_id="+id);
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
