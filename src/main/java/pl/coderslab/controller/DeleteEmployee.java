package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String approved = request.getParameter("aprove");
        try {
            int id = Integer.parseInt(approved);
            Employee employeeDel = EmployeeDao.loadById(id);
            if (employeeDel != null || employeeDel.getId() != 0) {
                EmployeeDao.deleteWorker(employeeDel);
                response.getWriter().append("Pracownik został usuniety <br />");
                response.getWriter().append(EmployeeDao.loadById(id).toString());
            }
        } catch (SQLException | NumberFormatException e) {
            response.getWriter().append("Nieprawidlowe id pracownika");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        response.getWriter().append("Servlet do usunięcia pracownika");
        String employeeId = request.getParameter("d");
        try {
            int id = Integer.parseInt(employeeId);
            Employee employeeTest = EmployeeDao.loadById(id);
            if (employeeTest != null && employeeTest.getId() != 0) {
                request.setAttribute("employee", employeeTest);
                request.getRequestDispatcher("/views/clearEmployee.jsp").forward(request, response);
                response.getWriter().append("Pracownik usuniety.<br />");
                response.getWriter().append(EmployeeDao.loadById(id).toString());
            }
        } catch (SQLException | NumberFormatException e) {
            response.getWriter().append("Nieprawidlowe id pracownika");
        }

    }
}
