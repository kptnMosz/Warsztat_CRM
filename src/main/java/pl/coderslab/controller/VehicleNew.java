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

@WebServlet(name = "VehicleNew", urlPatterns = "/VehicleNew")
public class VehicleNew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");
        String registration = request.getParameter("registration");
        String nextInspection = request.getParameter("nextInspection");
        String strCust = request.getParameter("owner");
        String strProduced = request.getParameter("produced");
        try {
            int custId = Integer.parseInt(strCust);
            int produced = Integer.parseInt(strProduced);
            Vehicle vehicle = new Vehicle(model, brand, produced, registration, nextInspection, custId);
            VehicleDao.saveToDb(vehicle);
            response.getWriter().append("dodano pojazd:" + vehicle);
        }catch (NumberFormatException e){
            e.printStackTrace();
            response.getWriter().append("Nieprawidlowy format danych");
        }catch (SQLException e){
            e.printStackTrace();
            response.getWriter().append("Błąd bazy danych");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        request.getRequestDispatcher("/views/vehicleNew.jsp").forward(request, response);
    }
}
