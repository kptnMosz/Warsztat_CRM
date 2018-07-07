package pl.coderslab.controller;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "VehicleCust", urlPatterns = "/VehicleCust")
public class VehicleCust extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        ArrayList<Vehicle> vehiclesForCustomer = new ArrayList<>();

        PrintWriter pisak = response.getWriter();
        String customer = request.getParameter("customerid");

        int custId = 0;
        try {
            custId = Integer.parseInt(customer);


            Customer pan = CustomerDao.loadById(custId);
            request.setAttribute("customer", pan);

            vehiclesForCustomer = VehicleDao.loadByCustomer(custId);
            request.setAttribute("vehicles", vehiclesForCustomer);
            request.getRequestDispatcher("/views/vehicleView.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            pisak.println("nieprawidlowy format id usera");
        }
    }
}
