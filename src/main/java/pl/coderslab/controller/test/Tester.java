package pl.coderslab.controller.test;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.VehicleDao;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "Tester", urlPatterns = "/testVehicle")
public class Tester extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html, charset:utf-8");

        PrintWriter pisak = response.getWriter();
        try {
            Vehicle vehicle = VehicleDao.loadById(2);
            pisak.println(vehicle.toString());
            pisak.println("<br> ------======zmieniamy rok produkcji====-------");
            vehicle.setProduced(new Random().nextInt(200)+1900);
            VehicleDao.saveToDb(vehicle);
            pisak.println("<br> ------======ponownie ściągamy z bazy====-------");
            vehicle = VehicleDao.loadById(2);
            pisak.println("<br>"+vehicle);
            pisak.println("<br> ------======tworzymy nowy samochod====-------");
            vehicle = new Vehicle("Renault","Traffic",2007,"WR" + (new Random().nextInt(200)+1900), LocalDate.of(2018,11,9),3);
            pisak.println("<br>sprawdzamy czy lyka zmiana daty ze stringa: "+vehicle.setNextInspectionFromString("2018-09-19"));
            pisak.println("<br>"+vehicle);
            pisak.println("<br> ------======zapisujemy do bazy====-------");
            VehicleDao.saveToDb(vehicle);
            pisak.println("<br>: "+vehicle);
            ArrayList<Vehicle> list = VehicleDao.loadAll(5);
            pisak.println("<br> ------======drukujemy 5 najnoowszych samochodow====-------");
            for(Vehicle veh : list){
                pisak.println("<br>"+veh);
            }
            pisak.println("<br> ------======trzeci element:====-------");
            vehicle =list.get(2);
            pisak.println("<br>"+vehicle);
            VehicleDao.delete(vehicle);

            pisak.println("<br> ------======kasujemy trzeci i sciagamy ponownie====-------");
            list = VehicleDao.loadAll(5);
            for(Vehicle veh : list){
                pisak.println("<br>"+veh);
            }


        } catch (SQLException e) {
            pisak.println("<br> zapis do bazy nie powiódł sie:<br>"+ e.getLocalizedMessage() + e.getMessage());
        }

        response.getWriter().append("<br> koniec testu");
    }
}
