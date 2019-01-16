/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viiews.movement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_ARTICLE;
import model.WB_CR_MOVEMENT;
import persistance.MovementPersistance;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "MovementInsert", urlPatterns = {"/MovementInsert"})
public class MovementInsert extends HttpServlet {

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
            out.println("<title>Servlet MovementInsert</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovementInsert at " + request.getContextPath() + "</h1>");
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
        if (!request.getParameter("name").equals("") && !request.getParameter("direction").equals("")) {
            try {
                for (WB_CR_MOVEMENT movement : MovementPersistance.getInstance().getObjectList()) {
                    if (!Objects.equals(movement.getMovement_id(), id)) {
                        continue;
                    }
                    id++;
                }
                WB_CR_MOVEMENT inserted_record = new WB_CR_MOVEMENT(
                        id,
                        request.getParameter("name"),
                        request.getParameter("direction"),
                        "CREATED");
                persistance.MovementPersistance.getInstance().getObjectList().add(inserted_record);
                response.sendRedirect("/CR_WB_Project/MovementServlet");
            } catch (IOException | NumberFormatException e) {
                response.setContentType("text/html");
                out.println("<script> alert(" + e.getMessage() + "); </script>");
                response.sendRedirect("/CR_WB_Project/MovementServlet");
            }
        } else {
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar datos antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_Project/MovementServlet");
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
