package pl.coderslab.controller;

import pl.coderslab.DateUtil;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ReportView", urlPatterns = "/ReportView")
public class ReportView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");
        if ("revenue".equals(request.getParameter("report"))) {
            Date start = DateUtil.setDateSqlFromString(request.getParameter("start"));
            Date end = DateUtil.setDateSqlFromString(request.getParameter("end"));
            if(end!=null&&start!=null){
                HashMap<String, BigDecimal> reportData = OrderDao.revenueReport(start, end);
                request.setAttribute("reportData", reportData);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.getRequestDispatcher("/views/reportRevenue.jsp").forward(request, response);
                return;
            }
            response.getWriter().append("niepoprawna data spróbuj ponownie");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");

        request.getRequestDispatcher("/views/reportView.jsp").forward(request, response);
    }
}
