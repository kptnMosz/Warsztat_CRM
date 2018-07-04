package pl.coderslab.controller.test;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "CustomerTest", urlPatterns = "/CustomerTest")
public class CustomerTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        CustomerDao customerDao = new CustomerDao();

        //loading all test (great success) - returns ArrayList<Customer>
//        ArrayList<Customer> testLista = customerDao.loadAll();
////        for (int i = 0; i < testLista.size()-1; i++) {
////            response.getWriter().append(Arrays.toString(testLista.toArray()));
////            response.getWriter().append("<br>");
////        }

        //adding test (great success) - if id exists it will edit the customer
//        try {
//            customerDao.saveToDb(new Customer(3,"Janek", "Gutkowski", "1997-06-10", "jan7.gutek@gmail.com"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        //loading by id (great success) - returns Customer class object
//        Customer byId = customerDao.loadById(new Customer(1));
//        response.getWriter().append(byId.toString());

        //delete by id  test (success unless there is a vehicle with the customer id that i want to delete)
//        customerDao.delete(new Customer(2));


        response.getWriter().append("halo");
    }
}
