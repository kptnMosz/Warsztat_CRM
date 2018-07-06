package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.StatusDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OrderNew", urlPatterns = "/OrderNew")
public class OrderNew extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        PrintWriter pisak = response.getWriter();


        Order order = new Order();

//        -------przypisujemy status-------
        try {
            String status = request.getParameter("status");
            if (status != null && !status.equals("")) {
                int statusInt = Integer.parseInt(status);
                order.setStatusId(statusInt);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("nieprawidlowy status, nie zmieniono<br>");
        }

//        -------przypisujemy pracownika-------
        try {
            String employStr = request.getParameter("employeeId");
            if (employStr != null && !employStr.equals("")) {
                int employeeId = Integer.parseInt(employStr);
                    order.setStatusId(employeeId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("nieprawidlowy status, nie zmieniono<br>");
        }

//        -------przypisujemy poczatek naprawy-------
        try {
            String startFixStr = request.getParameter("startFix");
            if (startFixStr != null && startFixStr.equals("")) {
                order.setStartFix(startFixStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd bazy danych - start Fix, nie zmieniono danych<br>");
        }

//        -------przypisujemy planowany termin naprawy-------

        pisak.println(request.getParameter("plannedFix"));
        try {
            String plannedFix = request.getParameter("plannedFix");
            if (plannedFix != null && !plannedFix.equals("")) {
                order.setPlannedFix(plannedFix);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd bazy danych - planned Fix, nie zmieniono danych<br>");
        }

//        -------przypisujemy price-------
        try {
            String priceStr = request.getParameter("price");
            if (priceStr != null && !priceStr.equals("")) {
                BigDecimal bdPrice = new BigDecimal(priceStr);
                order.setPrice(bdPrice);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd bazy danych - price, nie zmieniono danych<br>");
        }
//                -------przypisujemy parts cost-------

        try {
            String partsStr = request.getParameter("partsCost");
            if (partsStr != null && !partsStr.equals("")) {
                BigDecimal bdParts = new BigDecimal(partsStr);
                order.setPartsCost(bdParts);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd bazy danych - partsCost, nie zmieniono danych<br>");
        }
        //        -------opis problemu-------
        try {
            String problemDesc = request.getParameter("problemDesc");
            if (problemDesc != null && !problemDesc.equals("")) {
                order.setProblemDesc(problemDesc);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd bazy danych - problemDesc, nie zmieniono danych<br>");
        }
        //        -------opis naprawy-------
        pisak.println(request.getParameter("fixDesc"));
        try {
            String fixDesc = request.getParameter("fixDesc");
            if (fixDesc != null && !fixDesc.equals("")) {
                order.setFixDesc(fixDesc);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd bazy danych - problemDesc, nie zmieniono danych<br>");
        }

//        -------przypisujemy robocizne-------
        try {
            String workhours = request.getParameter("workhours");
            if (workhours != null && !workhours.equals("")) {
                int workhoursInt = Integer.parseInt(workhours);
                order.setWorkhours(workhoursInt);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("nieprawidlowa wartosc rbh, nie zmieniono<br>");
        }
        OrderDao.saveOrUpdate(order);
        order = OrderDao.loadById(order.getId());
        pisak.println("<hr>nowe dane:<br>"+order);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");

            request.getRequestDispatcher("views/orderNew.jsp").forward(request, response);



    }
}
