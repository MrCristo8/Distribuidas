/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.TempArrays;
import model.WB_CR_USER;
import persistance.UserPersistance;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "UserUpdate", urlPatterns = {"/UserUpdate"})
public class UserUpdate extends HttpServlet {

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
            out.println("<title>Servlet UserUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserUpdate at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("user_name");
        String pwd = request.getParameter("user_password");
        int pos = persistance.UserPersistance.getInstance().getObjectList().indexOf(new WB_CR_USER(name, pwd));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/user/userUpdate.jsp");
        request.setAttribute("user", persistance.UserPersistance.getInstance().getObjectList().get(pos));
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
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String pwd = request.getParameter("password");
        if (!request.getParameter("name").equals("") && !request.getParameter("pwd").equals("") && !request.getParameter("permission").equals("")) {
            String state = "UPDATED";
            int pos = persistance.UserPersistance.getInstance().
                    getObjectList().indexOf(new WB_CR_USER(name, pwd));
            WB_CR_USER user = UserPersistance.getInstance().getObjectList().get(pos);
            if (persistance.UserPersistance.getInstance().getObjectList().get(pos).getState().equals("CREATED")) {
                state = "CREATED";
            }
            WB_CR_USER updated_record = new WB_CR_USER(
                    user.getUser_id(),
                    request.getParameter("name"),
                    request.getParameter("pwd"),
                    request.getParameter("permission"),
                    state);
            persistance.UserPersistance.getInstance().
                    getObjectList().remove(pos);
            persistance.UserPersistance.getInstance().
                    getObjectList().add(pos, updated_record);
            response.sendRedirect("/CR_WB_WebPage/UserServlet");
        } else {
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar datos antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_WebPage/UserUpdate?user_name=" + name + "&user_pwd=" + pwd);
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
