/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.TempArrays;
import model.WB_CR_BILL;
import model.WB_CR_USER;

/**
 *
 * @author wason
 */
@WebServlet(name = "BillDelete", urlPatterns
        = {
            "/BillDelete"
        })
public class BillDelete extends HttpServlet {

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
            out.println("<title>Servlet BillDelete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillDelete at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("bill_id"));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/bill/billDelete.jsp");
        request.setAttribute("bill_id", id);
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
        Integer id = Integer.parseInt(request.getParameter("bill_id"));
        String state = "DELETED";
        int count = persistance.BillDetailPersistance.getInstance().getObjectList().size();
        for (int i = 0; i < count; i++) {
            model.WB_CR_BILLDETAIL temp_bill_item = persistance.BillDetailPersistance.getInstance().getObjectList().get(i);
            if (temp_bill_item.getState().equals("CREATED") && temp_bill_item.getBill_id().equals(id)) {
                persistance.BillDetailPersistance.getInstance().getObjectList().remove(i);
            } else if (temp_bill_item.getBill_id().equals(id)) {
                persistance.BillDetailPersistance.getInstance().getObjectList().get(i).setState(state);
            }
        }
        int pos = persistance.BillPersistance.getInstance().getObjectList().indexOf(new WB_CR_BILL(id));

        if (persistance.BillPersistance.getInstance().getObjectList().get(pos).getState().equals("CREATED")) {
            persistance.BillPersistance.getInstance().getObjectList().remove(pos);
        } else {
            persistance.BillPersistance.getInstance().getObjectList().get(pos).setState(state);
        }
        response.sendRedirect("/CR_WB_WebPage/BillServlet");
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
