package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDao {
    public static ArrayList<Vehicle> loadAll() throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("SELECT id, model , brand, produced, registration, next_inspection FROM vehicles;");


        return list;
    }
}
