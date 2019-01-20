/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.article;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WB_CR_ARTICLE;
import model.WB_CR_USER;
import persistance.UserPersistance;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "ArticleUpdate", urlPatterns = {"/ArticleUpdate"})
public class ArticleUpdate extends HttpServlet {

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
            out.println("<title>Servlet ArticleUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArticleUpdate at " + request.getContextPath() + "</h1>");
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
        Integer id = Integer.parseInt(request.getParameter("article_id"));
        int pos = persistance.ArticlePersistance.getInstance().getObjectList().indexOf(new WB_CR_ARTICLE(id));
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/article/articleUpdate.jsp");
        request.setAttribute("article", persistance.ArticlePersistance.getInstance().getObjectList().get(pos));
        int current = 0;
        for (WB_CR_USER user : UserPersistance.getInstance().getObjectList()) {
            if (user.getState().equals("CURRENT")) {
                break;
            }
            current++;
        }
        if (current < UserPersistance.getInstance().getObjectList().size()) {
            String[] permission = UserPersistance.getInstance().getObjectList().get(current).getUser_permission().split(",");
            request.setAttribute("user", permission);
        } else {
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
            response.sendRedirect("/CR_WB_WebPage/UserServlet");
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        if (!request.getParameter("name").equals("") && !request.getParameter("price").equals("") && !request.getParameter("stock").equals("")) {
            try {
                String state = "UPDATED";
                int stock = Integer.parseInt(request.getParameter("stock"));
                if (stock >= 0) {
                    int pos = persistance.ArticlePersistance.getInstance().
                            getObjectList().indexOf(new WB_CR_ARTICLE(id));
                    if (persistance.ArticlePersistance.getInstance().getObjectList().get(pos).getState().equals("CREATED")) {
                        state = "CREATED";
                    }
                    WB_CR_ARTICLE updated_record = new WB_CR_ARTICLE(
                            id,
                            request.getParameter("name"),
                            Float.parseFloat(request.getParameter("price")),
                            stock,
                            state);
                    persistance.ArticlePersistance.getInstance().
                            getObjectList().remove(pos);
                    persistance.ArticlePersistance.getInstance().
                            getObjectList().add(pos, updated_record);
                    response.sendRedirect("/CR_WB_WebPage/ArticleServlet");
                } else {
                    response.setContentType("text/html");
                    out.println("<script> alert('No se puede ingresar un sock negativo'); </script>");
                    response.sendRedirect("/CR_WB_WebPage/ArticleUpdate?article_id="+id);
                }
            } catch (NumberFormatException e) {
                response.setContentType("text/html");
                out.println("<script> alert(" + e.getMessage() + "); </script>");
                response.sendRedirect("/CR_WB_WebPage/ArticleUpdate?article_id="+id);
            }
        } else {
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar datos antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_WebPage/ArticleUpdate?article_id="+id);
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
