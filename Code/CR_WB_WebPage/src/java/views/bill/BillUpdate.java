/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_BILL;
import model.WB_CR_BILLDETAIL;

/**
 *
 * @author wason
 */
@WebServlet(name = "BillUpdate", urlPatterns =
{
    "/BillUpdate"
})
public class BillUpdate extends HttpServlet
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
            out.println("<title>Servlet BillUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillUpdate at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("bill_id"));
        int pos = persistance.BillPersistance.getInstance().getObjectList().indexOf(new WB_CR_BILL(id));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/bill/billUpdate.jsp");
        request.setAttribute("bill", persistance.BillPersistance.getInstance().getObjectList().get(pos));
        request.setAttribute("city_arr", persistance.CityPersistance.getInstance().getObjectList());
        request.setAttribute("client_arr", persistance.ClientPersistance.getInstance().getObjectList());
        request.setAttribute("article_arr", persistance.ArticlePersistance.getInstance().getObjectList());
        persistance.BillDetailPersistance.getInstance().getObjectList().forEach(x ->
        {
            if (x.getBill_id().equals(id) && !logic.TempArrays.getInstance().getTempBillDetailArr().contains(x))
            {
                logic.TempArrays.getInstance().getTempBillDetailArr().add(x);
            }
        });
        request.setAttribute("detail_arr", logic.TempArrays.getInstance().getTempBillDetailArr());
        if (dispatcher != null)
        {
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