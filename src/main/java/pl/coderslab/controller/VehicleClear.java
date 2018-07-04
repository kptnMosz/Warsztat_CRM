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

@WebServlet(name = "VehicleClear", urlPatterns = "views/VehicleClear")
public class VehicleClear extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String zgoda =request.getParameter("aprove");
        response.getWriter().append(zgoda);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        response.getWriter().append("jestem w serwlecie Vehicle Clear");
        String vehId = request.getParameter("id");
        try{
            int id = Integer.parseInt(vehId);
            Vehicle vehicleTest = VehicleDao.loadById(id);
            if(vehicleTest==null||vehicleTest.getId()==0) {
                request.setAttribute("vehicle", vehicleTest);
                request.getRequestDispatcher("/Views/vehicleClear.jsp").forward(request, response);
            }
        }catch (SQLException | NumberFormatException e){
            response.getWriter().append("Nieprawidlowe id pojazdu");
        }

    }
}
