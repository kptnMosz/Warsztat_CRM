package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/ModifyEmployee")
public class ModifyEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8;");
        request.setCharacterEncoding("UTF-8");
        String id= request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String adress = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String notes = request.getParameter("notes");
        String wage=request.getParameter("wage");
        BigDecimal empWage= BigDecimal.valueOf(Integer.parseInt(wage));

//        try {
//            int empId = Integer.parseInt(id);
//            Employee employeeDel = EmployeeDao.update(empId);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/editEmployee.jsp").forward(request, response);

    }
}
