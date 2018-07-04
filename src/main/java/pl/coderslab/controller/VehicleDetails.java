package pl.coderslab.controller;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VehicleDetails", urlPatterns = "/VehicleDetails")
public class VehicleDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        response.getWriter().append("jestem w serwlecie Vehicle Details");
        String vehId = request.getParameter("id");
        try {
            int id = Integer.parseInt(vehId);
            Vehicle vehicleTest = VehicleDao.loadById(id);
            if (vehicleTest != null && vehicleTest.getId() != 0) {
                if ("edit".equals(request.getParameter("mode"))) {
                    //todo znalezc lepszy sposob na ponizsze
                    request.setAttribute("editable", true);
                } else {
                    request.setAttribute("editable", "");
                }

                request.setAttribute("vehicle", vehicleTest);
                //todo lista napraw
                request.getRequestDispatcher("/views/vehicleDetails.jsp").forward(request, response);
            } else {
                response.getWriter().append("Nie udało sie załadowac pojazdu o tym id");
            }
        } catch (SQLException | NumberFormatException e) {
            response.getWriter().append("Nieprawidlowe id pojazdu");
        }
    }
}
