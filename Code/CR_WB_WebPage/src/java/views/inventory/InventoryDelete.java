/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.inventory;

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
import model.WB_CR_INVENTORY;
import model.WB_CR_USER;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "InventoryDelete", urlPatterns = {"/InventoryDelete"})
public class InventoryDelete extends HttpServlet {

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
            out.println("<title>Servlet InventoryDelete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryDelete at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("inventory_id"));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/inventory/inventoryDelete.jsp");
        request.setAttribute("inventory_id", id);
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
        Integer id = Integer.parseInt(request.getParameter("inventory_id"));
        String state = "DELETED";
        int count = persistance.InventoryDetailPersistance.getInstance().getObjectList().size();
        for (int i = 0; i < count; i++) {
            model.WB_CR_INVENTORY_DETAIL temp_inventory_item = persistance.InventoryDetailPersistance.getInstance().getObjectList().get(i);
            if (temp_inventory_item.getState().equals("CREATED") && temp_inventory_item.getInventory_id().equals(id)) {
                persistance.InventoryDetailPersistance.getInstance().getObjectList().remove(i);
            } else if (temp_inventory_item.getInventory_id().equals(id)) {
                persistance.InventoryDetailPersistance.getInstance().getObjectList().get(i).setState(state);
            }
        }
        int pos = persistance.InventoryPersistance.getInstance().getObjectList().indexOf(new WB_CR_INVENTORY(id));
        if (persistance.InventoryPersistance.getInstance().getObjectList().get(pos).getState().equals("CREATED")) {
            persistance.InventoryPersistance.getInstance().getObjectList().remove(pos);
        } else {
            persistance.InventoryPersistance.getInstance().getObjectList().get(pos).setState(state);
        }
        response.sendRedirect("/CR_WB_WebPage/InventoryServlet");
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
