/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.inventorydeteail;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_INVENTORY_DETAIL;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "InventoryDetailDelete", urlPatterns = {"/InventoryDetailDelete"})
public class InventoryDetailDelete extends HttpServlet {

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
            out.println("<title>Servlet InventoryDetailDelete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryDetailDelete at " + request.getContextPath() + "</h1>");
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
        Integer article_id = Integer.parseInt(request.getParameter("article_id"));
        Integer inventory_id = Integer.parseInt(request.getParameter("inventory_id"));
        WB_CR_INVENTORY_DETAIL selected_detail = new WB_CR_INVENTORY_DETAIL(inventory_id, article_id);
        
        String op = request.getParameter("op");
        if (op.equals("insert"))
        {
            op = "InventoryInsert";
            logic.TempArrays.getInstance().getTempInventoryDetailArr().remove(selected_detail);
        } else if (op.equals("update"))
        {
            op = "InventoryUpdate?inventory_id=" + inventory_id;
            int pos = persistance.InventoryDetailPersistance.getInstance().getObjectList().indexOf(selected_detail);
            persistance.InventoryDetailPersistance.getInstance().getObjectList().get(pos).setState("DELETED");
            pos = logic.TempArrays.getInstance().getTempInventoryDetailArr().indexOf(selected_detail);
            logic.TempArrays.getInstance().getTempInventoryDetailArr().get(pos).setState("DELETED");
        }
        
        response.sendRedirect("/CR_WB_WebPage/" + op);
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
        processRequest(request, response);
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
