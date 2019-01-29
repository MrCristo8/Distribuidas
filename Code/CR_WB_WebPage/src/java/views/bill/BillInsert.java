/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.TempArrays;
import model.WB_CR_BILL;
import model.WB_CR_BILLDETAIL;
import model.WB_CR_USER;

/**
 *
 * @author wason
 */
@WebServlet(name = "BillInsert", urlPatterns
        =
        {
            "/BillInsert"
        })
public class BillInsert extends HttpServlet
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
            out.println("<title>Servlet BillInsert</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillInsert at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/bill/billInsert.jsp");
        request.setAttribute("city_arr", persistance.CityPersistance.getInstance().getObjectList());
        request.setAttribute("client_arr", persistance.ClientPersistance.getInstance().getObjectList());
        request.setAttribute("article_arr", persistance.ArticlePersistance.getInstance().getObjectList());
        request.setAttribute("detail_arr", logic.TempArrays.getInstance().getTempBillDetailArr());
        Integer id = 1;
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
        for (WB_CR_BILL bill : persistance.BillPersistance.getInstance().getObjectList())
        {
            if (!Objects.equals(bill.getBill_id(), id))
            {
                break;
            }
            id++;
        }
        request.setAttribute("bill_id", id);
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
        try
        {
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
            Date bill_date = new Date(request.getParameter("bill_date").replace("-", "/"));
            Integer bill_id = Integer.parseInt(request.getParameter("bill_id"));
            Integer client_id = Integer.parseInt(request.getParameter("client"));
            Integer city_id = Integer.parseInt(request.getParameter("city"));

            HashMap<Integer, model.WB_CR_BILLDETAIL> detail_list = new HashMap<>();
            logic.TempArrays.getInstance().getTempBillDetailArr().forEach(x
                    ->
            {
                if (!detail_list.containsKey(x.getArticle_id()))
                {
                    detail_list.put(x.getArticle_id(), x);
                } else
                {
                    model.WB_CR_BILLDETAIL temp = detail_list.get(x.getArticle_id());
                    temp.setDetail_ammount(temp.getDetail_ammount() + x.getDetail_ammount());
                    detail_list.replace(x.getArticle_id(), temp);
                }
            });
            WB_CR_BILL bill_item = new WB_CR_BILL(bill_id, bill_date, client_id, city_id, "CREATED");
            int pos = persistance.ClientPersistance.getInstance().getObjectList().indexOf(new model.WB_CR_CLIENT(client_id));
            bill_item.setClient(persistance.ClientPersistance.getInstance().getObjectList().get(pos));
            pos = persistance.CityPersistance.getInstance().getObjectList().indexOf(new model.WB_CR_CITY(city_id));
            bill_item.setCity(persistance.CityPersistance.getInstance().getObjectList().get(pos));
            persistance.BillPersistance.getInstance().getObjectList().add(
                    bill_item);
            detail_list.entrySet().forEach((Map.Entry<Integer, WB_CR_BILLDETAIL> me)
                    ->
            {
                WB_CR_BILLDETAIL detail_item = me.getValue();
                model.WB_CR_ARTICLE article_from_detail = detail_item.getArticle();
                Integer updated_stock = article_from_detail.getArticle_stock() - detail_item.getDetail_ammount();
                if (updated_stock >= 0)
                {
                    article_from_detail.setArticle_stock(updated_stock);
                    article_from_detail.setState("UPDATED");
                    int art_pos = persistance.ArticlePersistance.getInstance().getObjectList().indexOf(article_from_detail);
                    persistance.ArticlePersistance.getInstance().getObjectList().remove(art_pos);
                    persistance.ArticlePersistance.getInstance().getObjectList().add(art_pos, article_from_detail);
                    detail_item.setState("CREATED");
                    persistance.BillDetailPersistance.getInstance().getObjectList().add(
                            detail_item);
                }

            });
            logic.TempArrays.getInstance().getTempBillDetailArr().clear();
            response.sendRedirect("/CR_WB_WebPage/BillServlet");

        } catch (IOException | NumberFormatException ex)
        {
            PrintWriter out = response.getWriter();
            System.out.println(ex.getMessage());
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar un valor valido antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_WebPage/BillServlet");
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
