/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.*;
import model.WB_CR_USER;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "ClientServlet", urlPatterns
        =
        {
            "/ClientServlet"
        })
public class ClientServlet extends HttpServlet
{

    public ClientServlet()
    {
        if (persistance.ClientPersistance.getInstance().getObjectList().isEmpty())
        {
            try
            {
                persistance.ClientPersistance.getInstance().loadObjectList();
            } catch (RemoteException ex)
            {
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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientServlet at " + request.getContextPath() + "</h1>");
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
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/client/clientList.jsp");
        request.setAttribute("objList", persistance.ClientPersistance.getInstance().getObjectList());
        if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER()))
        {
            String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
            Arrays.sort(permission);
            request.setAttribute("permission", permission);
            request.setAttribute("user_name", TempArrays.getInstance().getUser().getUser_name());

        } else
        {
            request.setAttribute("permission", new String[]
            {
            });
            request.setAttribute("user_name", "temp_user");
        }
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
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/client/clientList.jsp");
        ArrayList<model.WB_CR_CLIENT> filtered_list = new ArrayList<>();
        persistance.ClientPersistance.getInstance().getObjectList().forEach(x
                ->
        {
            if (x.getClient_name().toUpperCase().contains(request.getParameter("search_string").toUpperCase()))
            {
                filtered_list.add(x);
            }
        });
        request.setAttribute("objSearchList",
                filtered_list);
        request.setAttribute("objList",
                persistance.ClientPersistance.getInstance().getObjectList());
        if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER()))
        {
            String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
            Arrays.sort(permission);
            request.setAttribute("permission", permission);
            request.setAttribute("user_name", TempArrays.getInstance().getUser().getUser_name());

        } else
        {
            request.setAttribute("permission", new String[]
            {
            });
            request.setAttribute("user_name", "temp_user");
        }
        if (dispatcher != null)
        {
            dispatcher.forward(request, response);
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
