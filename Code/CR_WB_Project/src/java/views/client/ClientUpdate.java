/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CR_WB_Client;

/**
 *
 * @author wason
 */
@WebServlet(name = "ClientUpdate", urlPatterns =
{
    "/ClientUpdate"
})
public class ClientUpdate extends HttpServlet
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
            out.println("<title>Servlet ClientUpdate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientUpdate at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("client_id"));
        int pos = persistance.ClientPersistance.getInstnace().getObjectList().indexOf(new CR_WB_Client(id));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/WEB-INF/client/clientupdate.jsp");
        request.setAttribute("client", persistance.ClientPersistance.getInstnace().getObjectList().get(pos));
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        String state = "UPDATED";
        int pos = persistance.ClientPersistance.getInstnace().
                getObjectList().indexOf(new CR_WB_Client(id));
        if (persistance.ClientPersistance.getInstnace().getObjectList().get(pos).getState().equals("CREATED"))
        {
            state = "CREATED";
        }
        CR_WB_Client updated_record = new CR_WB_Client(
                id,
                request.getParameter("dni"),
                request.getParameter("name"),
                request.getParameter("addr"),
                state);
        persistance.ClientPersistance.getInstnace().
                getObjectList().remove(pos);
        persistance.ClientPersistance.getInstnace().
                getObjectList().add(pos, updated_record);        
        response.sendRedirect("/CR_WB_Project/ClientServlet");
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
