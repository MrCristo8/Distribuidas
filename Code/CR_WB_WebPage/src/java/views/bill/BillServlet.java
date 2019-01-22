/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.bill;

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
import logic.TempArrays;
import model.WB_CR_USER;

/**
 *
 * @author wason
 */
@WebServlet(name = "BillServlet", urlPatterns
        = {
            "/BillServlet"
        })
public class BillServlet extends HttpServlet {

    public BillServlet() {
        if (persistance.BillPersistance.getInstance().getObjectList().isEmpty()) {
            try {
                persistance.BillPersistance.getInstance().loadObjectList();
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
            out.println("<title>Servlet BillServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillServlet at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/bill/billList.jsp");
        request.setAttribute("objList",
                persistance.BillPersistance.getInstance().getObjectList());
        if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER())) {
            String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
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
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/bill/billList.jsp");
        ArrayList<model.WB_CR_BILL> filtered_list = new ArrayList<>();
        persistance.BillPersistance.getInstance().getObjectList().forEach(x
                -> {
            if (x.getClient().getClient_name().toUpperCase().contains(request.getParameter("search_string").toUpperCase())) {
                filtered_list.add(x);
            }
        });
        request.setAttribute("objSearchList",
                filtered_list);
        request.setAttribute("objList",
                persistance.BillPersistance.getInstance().getObjectList());
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
