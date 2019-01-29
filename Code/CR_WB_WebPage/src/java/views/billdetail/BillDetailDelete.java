/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.billdetail;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_BILLDETAIL;

/**
 *
 * @author wason
 */
@WebServlet(name = "BillDetailDelete", urlPatterns =
{
    "/BillDetailDelete"
})
public class BillDetailDelete extends HttpServlet
{

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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BillDetailDelete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillDetailDelete at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException
    {
        Integer article_id = Integer.parseInt(request.getParameter("article_id"));
        Integer bill_id = Integer.parseInt(request.getParameter("bill_id"));
        WB_CR_BILLDETAIL selected_detail = new WB_CR_BILLDETAIL(bill_id, article_id);
        
        String op = request.getParameter("op");
        if (op.equals("insert"))
        {
            op = "BillInsert";
            logic.TempArrays.getInstance().getTempBillDetailArr().remove(selected_detail);
        } else if (op.equals("update"))
        {
            op = "BillUpdate?bill_id=" + bill_id;
            int pos = persistance.BillDetailPersistance.getInstance().getObjectList().indexOf(selected_detail);
            persistance.BillDetailPersistance.getInstance().getObjectList().get(pos).setState("DELETED");
            pos = logic.TempArrays.getInstance().getTempBillDetailArr().indexOf(selected_detail);
            logic.TempArrays.getInstance().getTempBillDetailArr().get(pos).setState("DELETED");
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
