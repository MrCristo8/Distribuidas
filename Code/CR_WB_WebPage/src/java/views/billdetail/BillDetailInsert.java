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
import model.WB_CR_ARTICLE;
import model.WB_CR_BILLDETAIL;

/**
 *
 * @author wason
 */
@WebServlet(name = "BillDetailInsert", urlPatterns =
{
    "/BillDetailInsert"
})
public class BillDetailInsert extends HttpServlet
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
            out.println("<title>Servlet BillDetailInsert</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillDetailInsert at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException
    {
        Integer article_id = Integer.parseInt(request.getParameter("article"));
        Integer bill_id = Integer.parseInt(request.getParameter("bill_id"));
        Integer detail_ammount = 0;
        try
        {
            detail_ammount = Integer.parseInt(request.getParameter("detail_amount"));
            WB_CR_BILLDETAIL detail_entry = new WB_CR_BILLDETAIL(bill_id, detail_ammount, article_id, "CREATED");
            model.WB_CR_ARTICLE article = new WB_CR_ARTICLE(article_id);
            int pos = persistance.ArticlePersistance.getInstance().getObjectList().indexOf(article);
            article = persistance.ArticlePersistance.getInstance().getObjectList().get(pos);
            detail_entry.setArticle(article);
            logic.TempArrays.getInstance().getTempBillDetailArr().add(detail_entry);
            response.sendRedirect("/CR_WB_WebPage/BillInsert");
        } catch (NumberFormatException ex)
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar un valor antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_WebPage/BillInsert");
            System.out.println(ex.getMessage());
        }

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
