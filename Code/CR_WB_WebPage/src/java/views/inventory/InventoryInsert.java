/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.inventory;

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
import model.WB_CR_INVENTORY;
import model.WB_CR_INVENTORY_DETAIL;
import model.WB_CR_USER;

/**
 *
 * @author csrm1
 */
@WebServlet(name = "InventoryInsert", urlPatterns = {"/InventoryInsert"})
public class InventoryInsert extends HttpServlet {

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
            out.println("<title>Servlet InventoryInsert</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryInsert at " + request.getContextPath() + "</h1>");
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
        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/inventory/inventoryInsert.jsp");
        request.setAttribute("movement_arr", persistance.MovementPersistance.getInstance().getObjectList());
        request.setAttribute("article_arr", persistance.ArticlePersistance.getInstance().getObjectList());
        request.setAttribute("detail_arr", logic.TempArrays.getInstance().getTempInventoryDetailArr());
        Integer id = 1;
        if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER())) {
            String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
            Arrays.sort(permission);
            request.setAttribute("permission", permission);
            request.setAttribute("user_name", TempArrays.getInstance().getUser().getUser_name());

        } else {
            request.setAttribute("permission", new String[]{});
            request.setAttribute("user_name", "temp_user");
        }
        for (WB_CR_INVENTORY inventory : persistance.InventoryPersistance.getInstance().getObjectList()) {
            if (!Objects.equals(inventory.getInventory_id(), id)) {
                break;
            }
            id++;
        }
        request.setAttribute("inventory_id", id);
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
        try {
            if (!TempArrays.getInstance().getUser().equals(new WB_CR_USER())) {
                String[] permission = TempArrays.getInstance().getUser().getUser_permission().split(",");
                Arrays.sort(permission);
                request.setAttribute("permission", permission);
                request.setAttribute("user_name", TempArrays.getInstance().getUser().getUser_name());

            } else {
                request.setAttribute("permission", new String[]{});
                request.setAttribute("user_name", "temp_user");
            }
            Date inventory_date = new Date(request.getParameter("inventory_date").replace("-", "/"));
            Integer inventory_id = Integer.parseInt(request.getParameter("inventory_id"));
            Integer movement_id = Integer.parseInt(request.getParameter("movement"));

            HashMap<Integer, WB_CR_INVENTORY_DETAIL> detail_list = new HashMap<>();
            logic.TempArrays.getInstance().getTempInventoryDetailArr().forEach(x
                    -> {
                if (!detail_list.containsKey(x.getArticle_id())) {
                    detail_list.put(x.getArticle_id(), x);
                } else {
                    model.WB_CR_INVENTORY_DETAIL temp = detail_list.get(x.getArticle_id());
                    temp.setArticle_ammount(temp.getArticle_ammount() + x.getArticle_ammount());
                    detail_list.replace(x.getArticle_id(), temp);
                }
            });
            WB_CR_INVENTORY inventory_item = new WB_CR_INVENTORY(inventory_id, movement_id, inventory_date, "CREATED");
            int pos = persistance.MovementPersistance.getInstance().getObjectList().indexOf(new model.WB_CR_MOVEMENT(movement_id));
            inventory_item.setMovement(persistance.MovementPersistance.getInstance().getObjectList().get(pos));
            persistance.InventoryPersistance.getInstance().getObjectList().add(inventory_item);
            detail_list.entrySet().forEach((Map.Entry<Integer, WB_CR_INVENTORY_DETAIL> me)
                    -> {
                WB_CR_INVENTORY_DETAIL detail_item = me.getValue();
                model.WB_CR_ARTICLE article_from_detail = detail_item.getArticle();
                Integer updated_stock;
                if (inventory_item.getMovement().getMovement_direction().equals("-")) {
                    updated_stock = article_from_detail.getArticle_stock() - detail_item.getArticle_ammount();
                } else {
                    updated_stock = article_from_detail.getArticle_stock() + detail_item.getArticle_ammount();
                }
                if (updated_stock >= 0) {
                    article_from_detail.setArticle_stock(updated_stock);
                    article_from_detail.setState("UPDATED");
                    int art_pos = persistance.ArticlePersistance.getInstance().getObjectList().indexOf(article_from_detail);
                    persistance.ArticlePersistance.getInstance().getObjectList().remove(art_pos);
                    persistance.ArticlePersistance.getInstance().getObjectList().add(art_pos, article_from_detail);
                    detail_item.setState("CREATED");
                    persistance.InventoryDetailPersistance.getInstance().getObjectList().add(
                            detail_item);
                }

            });
            logic.TempArrays.getInstance().getTempInventoryDetailArr().clear();
            response.sendRedirect("/CR_WB_WebPage/InventoryServlet");

        } catch (IOException | NumberFormatException ex) {
            PrintWriter out = response.getWriter();
            System.out.println(ex.getMessage());
            response.setContentType("text/html");
            out.println("<script> alert('Debes ingresar un valor valido antes de continuar'); </script>");
            response.sendRedirect("/CR_WB_WebPage/InventoryServlet");
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
