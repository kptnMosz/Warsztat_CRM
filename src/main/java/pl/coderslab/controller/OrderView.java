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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Order> openOrders = new ArrayList<>();
        PrintWriter pisak = response.getWriter();

        openOrders = (ArrayList<Order>) OrderDao.loadAll();
        request.setAttribute("orders",openOrders);
        request.getRequestDispatcher("/views/orderView.jsp").forward(request, response);
    }
}
