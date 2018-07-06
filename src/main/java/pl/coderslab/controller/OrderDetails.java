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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OrderDetails", urlPatterns = "/OrderDetails")
public class OrderDetails extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        PrintWriter pisak = response.getWriter();

        String idStr = request.getParameter("orderId");
        pisak.println(idStr);
        Order order = new Order();
//        ------zasysamy order--------
        try {
            if (idStr == null) {
                throw new NumberFormatException("null id");
            }
            int id = Integer.parseInt(idStr);
            order = OrderDao.loadById(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pisak.println("błąd numeru zlecenia<br>");
            return;
        }
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
                if(order.getEmployeeId()==0) {
                    order.setEmployeeId(employeeId);
                }else{
                    pisak.println("nie zmieniamy przypisanego pracownika - sory taki system <br />");
                }
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
                order.addWorkhours(workhoursInt);
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
        String idStr = request.getParameter("orderid");
        try {
            int id = Integer.parseInt(idStr);
            Order order = OrderDao.loadById(id);
            request.setAttribute("order", order);
            int[] availibleStatuses;
            if (order.getStatusId() == 1) {
                availibleStatuses = new int[]{2, 5};
            } else {
                availibleStatuses = new int[]{3, 4, 5, 6};
            }
            Map<Integer, String> statusMap = new HashMap<>();
            for (int stat : availibleStatuses) {
                statusMap.put(stat, StatusDao.getName(stat));
            }
            request.setAttribute("statusMap", statusMap);
            request.getRequestDispatcher("views/orderDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.getWriter().append("Nieprawidlowe id zlecenia <br /><a href='/OrderNew'> założyć nowe?</a>");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
