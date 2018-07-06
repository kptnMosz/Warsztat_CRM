package pl.coderslab.controller;

import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8;");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String adress = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String notes = request.getParameter("notes");
        String strWage = request.getParameter("wage");

        try {
            BigDecimal hourlyPayment = BigDecimal.valueOf(Integer.parseInt(strWage));
            Employee employee = new Employee(name, surname, adress, phone, phone, notes, hourlyPayment);
            response.getWriter().append("Do bazy danych został dodany " + employee);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().append("Nieprawidlowy format danych");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/addEmployee.jsp").forward(request, response);

    }
}
