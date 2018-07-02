package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VehicleDao {
    public static ArrayList<Vehicle> loadAll() throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles;");
        ResultSet rs = sql.executeQuery();
        while(rs.next()){
            String model=rs.getString("model");
            String brand=rs.getString("brand");
            int produced =rs.getInt("produced");
            String registration=rs.getString("registration");
            LocalDate nextInspection=rs.getDate("next_inspection").toLocalDate();
            int customerId = rs.getInt("customer_id");
            list.add(new Vehicle(model,brand,produced,registration,nextInspection, customerId));
        }


        return list;
    }
}
