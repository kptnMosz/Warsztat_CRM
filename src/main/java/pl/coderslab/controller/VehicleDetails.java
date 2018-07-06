package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "VehicleDetails", urlPatterns = "/VehicleDetails")
public class VehicleDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        String strId = request.getParameter("id");
        response.getWriter().append(strId + "<br>");
        try {
            int id = Integer.parseInt(strId);
            response.getWriter().append(id + "<br>");
            Vehicle vehicle = VehicleDao.loadById(id);
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String registration = request.getParameter("registration");
            String nextInspection = request.getParameter("nextInspection");
            boolean zmianadaty = vehicle.setNextInspectionFromString(nextInspection);
            if (!zmianadaty) {
                response.getWriter().append("<br>nie powiodła sie zmiana daty nastepnego przegladu na:" + nextInspection);
            }

            vehicle.setModel(model);
            vehicle.setBrand(brand);
            vehicle.setRegistration(registration);
            VehicleDao.saveToDb(vehicle);
            vehicle = VehicleDao.loadById(id);
            response.getWriter().append("<br />-------=======nowe dane pojazdu:========--------<br />" + vehicle.toString());
        } catch (SQLException | NumberFormatException e) {
            response.getWriter().append("Nieprawidlowe dane" + e.getMessage() + e.getCause());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        response.getWriter().append("jestem w serwlecie Vehicle Details");
        String vehId = request.getParameter("vehicleid");
        try {
            int id = Integer.parseInt(vehId);
            Vehicle vehicleTest = VehicleDao.loadById(id);
            if (vehicleTest != null && vehicleTest.getId() != 0) {
                if ("edit".equals(request.getParameter("mode"))) {
                    //todo znalezc lepszy sposob na ponizsze
                    request.setAttribute("editable", "true");
                } else {
                    request.setAttribute("editable", "");
                }

                request.setAttribute("vehicle", vehicleTest);

                ArrayList<Order> orderList = (ArrayList<Order>) OrderDao.loadAllbyVehicle(id);
                request.setAttribute("orders",orderList);
                request.getRequestDispatcher("/views/vehicleDetails.jsp").forward(request, response);
            } else {
                response.getWriter().append("Nie udało sie załadowac pojazdu o tym id");
            }
        } catch (NumberFormatException e) {
            response.getWriter().append("Nieprawidlowe id pojazdu");
        } catch (SQLException e){
            response.getWriter().append("Błąd bazy danych");
        }
    }
}
