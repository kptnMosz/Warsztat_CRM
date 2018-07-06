package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "OrderView", urlPatterns = "/OrderView")
public class OrderView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        String statIdStr =request.getParameter("filterStatusId");
        int statId =-1;
        try{
            statId = Integer.parseInt(statIdStr);
            request.setAttribute("filterStatusId",statId);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        String customerIdStr =request.getParameter("filterCustomerId");
        int custId =-1;
        try{
            custId = Integer.parseInt(customerIdStr);
            request.setAttribute("filterCustomerId",custId);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        ArrayList<Order> openOrders = OrderDao.filteredSearch(statId,custId,-1,"","");
        request.setAttribute("orders",openOrders);
        request.setAttribute("filterCustomerId",custId);
        request.setAttribute("filterStatusId",statId);
        request.getRequestDispatcher("/views/orderView.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        ArrayList<Order> openOrders = new ArrayList<>();
        openOrders = (ArrayList<Order>) OrderDao.loadAll();
        request.setAttribute("orders",openOrders);
        request.getRequestDispatcher("/views/orderView.jsp").forward(request, response);
    }
}
