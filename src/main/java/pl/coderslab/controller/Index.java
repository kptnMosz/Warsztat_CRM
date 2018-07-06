package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Index", urlPatterns = "/Index")
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        ArrayList<Order> openOrders = new ArrayList<>();
        PrintWriter pisak = response.getWriter();

        openOrders = (ArrayList<Order>) OrderDao.loadAllActualOrders();
        request.setAttribute("orders",openOrders);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);

    }
}
