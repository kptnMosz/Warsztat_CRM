package pl.coderslab.controller.test;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "OrderTest", urlPatterns = "/OrderTest")
public class OrderTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");

        PrintWriter pisak = response.getWriter();

        pisak.println("<br /> ------======nowe zamowienie====-------<br />");
        Order order = new Order(Date.valueOf("2018-09-06"), Date.valueOf("2018-09-16"), Date.valueOf("2018-09-16"), 2, "test", "", 2, 2, null, null, null, 0);
        pisak.println(order.toString());

        pisak.println("<br /> ------======zapis do bazy====-------<br />");
        OrderDao.saveOrUpdate(order);
        pisak.println(order.toString());


    }
}
