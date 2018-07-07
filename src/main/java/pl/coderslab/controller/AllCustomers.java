package pl.coderslab.controller;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AllCustomers")
public class AllCustomers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8;");

        CustomerDao customerDao = new CustomerDao();
        ArrayList<Customer> customers = CustomerDao.loadAll();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/views/allCustomers.jsp").forward(request, response);
    }
}
