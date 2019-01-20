/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viiews.movement;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_USER;
import persistance.UserPersistance;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "MovementServlet", urlPatterns
        = {
            "/MovementServlet"
        })
public class MovementServlet extends HttpServlet {

    public MovementServlet() {
        if (persistance.MovementPersistance.getInstance().getObjectList().isEmpty()) {
            try {
                persistance.MovementPersistance.getInstance().loadObjectList();
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

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
            out.println("<title>Servlet MovementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovementServlet at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/movement/movementList.jsp");
        request.setAttribute("objList", persistance.MovementPersistance.getInstance().getObjectList());
        int current = 0;
        for (WB_CR_USER user : UserPersistance.getInstance().getObjectList()) {
            if (user.getState().equals("CURRENT")) {
                break;
            }
            current++;
        }
        if (current < UserPersistance.getInstance().getObjectList().size()) {
            String[] permission = UserPersistance.getInstance().getObjectList().get(current).getUser_permission().split(",");
            request.setAttribute("user", permission);
        } else {
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
            response.sendRedirect("/CR_WB_WebPage/UserServlet");
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
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/movement/movementList.jsp");
        ArrayList<model.WB_CR_MOVEMENT> filtered_list = new ArrayList<>();
        persistance.MovementPersistance.getInstance().getObjectList().forEach(x
                -> {
            if (x.getMovement_name().toUpperCase().contains(request.getParameter("search_string").toUpperCase())) {
                filtered_list.add(x);
            }
        });
        request.setAttribute("objSearchList", filtered_list);
        request.setAttribute("objList", persistance.MovementPersistance.getInstance().getObjectList());
        if (dispatcher != null) {
            dispatcher.forward(request, response);
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
