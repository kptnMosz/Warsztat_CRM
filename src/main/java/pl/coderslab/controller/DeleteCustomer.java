package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCustomer",urlPatterns = "/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("customerId"));
        CustomerDao customerDao = new CustomerDao();
        Customer customer = new Customer(id);
        try {
            customerDao.delete(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("../AllCustomers");
    }
}
