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
import java.util.ArrayList;

@WebServlet(name = "OrderTest", urlPatterns = "/OrderTest")
public class OrderTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");

        Order orderNull = OrderDao.loadById(9);


        PrintWriter pisak = response.getWriter();
        pisak.println(orderNull.getAcceptanceToRepairInSql());
        pisak.println("<br /> ------======nowe zamowienie====-------<br />");
        Order order = new Order("2018-12-06", "2018-12-16", "2018-12-16", 2, "test", "", 2, 2, null, null, null, 0);
        pisak.println(order.toString());

        order.setStartFix("2018-12-31");
        pisak.println(order.toString());

        pisak.println("<br /> ------======zapis do bazy====-------<br />");
        OrderDao.saveOrUpdate(order);
        pisak.println(order.toString());
        pisak.println("<br /> ------======usuwanie z bazy====-------<br />");
        int id = order.getId();
        OrderDao.delete(order);
        order = OrderDao.loadById(id);
        pisak.println(order.toString());

        pisak.println("<br> ------======zlecenia pracownika====-------");
        ArrayList<Order> list =(ArrayList<Order>) OrderDao.loadAllbyEmployee(2);
        pisak.println("<br> długość listy" + list.size());
        for(Order ord : list){
            pisak.println("<br>"+ord.toString());
        }


    }
}
