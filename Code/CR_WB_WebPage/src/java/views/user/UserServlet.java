/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * @author csrm1
 */
@WebServlet(name = "UserServlet", urlPatterns =
{
    "/UserServlet"
})
public class UserServlet extends HttpServlet
{

    public UserServlet()
    {
        if (persistance.UserPersistance.getInstance().getObjectList().isEmpty())
        {
            try
            {
                persistance.UserPersistance.getInstance().loadObjectList();
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
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/user/userList.jsp");
        List<WB_CR_USER> obj = persistance.UserPersistance.getInstance().getObjectList();
        obj.stream().filter((user) -> (user.getState().equals("CURRENT"))).forEachOrdered((user) ->
        {
            obj.remove(user);
        });
        request.setAttribute("objList", obj);
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
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/user/userList.jsp");
        ArrayList<model.WB_CR_USER> filtered_list = new ArrayList<>();
        persistance.UserPersistance.getInstance().getObjectList().forEach(x
                ->
        {
            if (x.getUser_name().toUpperCase().contains(request.getParameter("search_string").toUpperCase()))
            {
                filtered_list.add(x);
            }
        });
        request.setAttribute("objSearchList",
                filtered_list);
        request.setAttribute("objList",
                persistance.UserPersistance.getInstance().getObjectList());
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
