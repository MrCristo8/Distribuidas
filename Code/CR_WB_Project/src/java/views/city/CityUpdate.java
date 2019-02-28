/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.city;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CR_WB_City;

/**
 *
 * @author wason
 */
@WebServlet(name = "CityUpdate", urlPatterns
        = {
            "/CityUpdate"
        })
public class CityUpdate extends HttpServlet {

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
            out.println("<title>Servlet CityUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CityUpdate at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("city_id"));
        int pos = persistance.CityPersistance.getInstnace().getObjectList().indexOf(new CR_WB_City(id));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/WEB-INF/city/cityupdate.jsp");
        request.setAttribute("city", persistance.CityPersistance.getInstnace().getObjectList().get(pos));
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        if (!request.getParameter("name").equals("")) {
            String state = "UPDATED";
            int pos = persistance.CityPersistance.getInstnace().
                    getObjectList().indexOf(new CR_WB_City(id));
            if (persistance.CityPersistance.getInstnace().getObjectList().get(pos).getState().equals("CREATED")) {
                state = "CREATED";
            }
            CR_WB_City updated_record = new CR_WB_City(
                    id,
                    request.getParameter("name"),
                    state);
            persistance.CityPersistance.getInstnace().
                    getObjectList().remove(pos);
            persistance.CityPersistance.getInstnace().
                    getObjectList().add(pos, updated_record);
            response.sendRedirect("/CR_WB_Project/CityServlet");
        }
        else{
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar un nombre antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_Project/CityUpdate?city_id="+id);
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