package pl.coderslab.controller;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Tester", urlPatterns = "/test")
public class Tester extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DbUtil.getConn()) {
            PreparedStatement sql = connection.prepareStatement("SELECT 1 FROM vehicles;");
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                response.getWriter().append(rs.getString(1));
            }else {
            response.getWriter().append("nie ma");}


        } catch (SQLException e) {
            response.getWriter().append(e.getMessage());
            e.printStackTrace();
        }

        response.getWriter().append("dupa");
    }
}
