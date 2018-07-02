package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class VehicleDao {
    public static ArrayList<Vehicle> loadAll() throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles;");

        return loadFromDb(sql);
    }

    public static ArrayList<Vehicle> loadAll(int limit) throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles ORDER BY next_inspection LIMIT = " + limit + ";");

        return loadFromDb(sql);
    }

    public static ArrayList<Vehicle> loadFromDb(PreparedStatement sql) throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String model = rs.getString("model");
            String brand = rs.getString("brand");
            int produced = rs.getInt("produced");
            String registration = rs.getString("registration");
            LocalDate nextInspection = rs.getDate("next_inspection").toLocalDate();
            int customerId = rs.getInt("customer_id");
            list.add(new Vehicle(model, brand, produced, registration, nextInspection, customerId));
        }
        return list;
    }

    public static Vehicle loadById(int id) throws SQLException {
        Vehicle vehicle = null;
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles WHERE id=" + id + ";");
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {
            String model = rs.getString("model");
            String brand = rs.getString("brand");
            int produced = rs.getInt("produced");
            String registration = rs.getString("registration");
            LocalDate nextInspection = rs.getDate("next_inspection").toLocalDate();
            int customerId = rs.getInt("customer_id");
            vehicle = new Vehicle(id, model, brand, produced, registration, nextInspection, customerId);
        }
        return vehicle;
    }

    public static void saveToDb (Vehicle vehicle) throws SQLException {
        if(vehicle==null){
            throw new NullPointerException("Not possible to save a vehicle that does not exist");
        }
        if(vehicle.getId()!=0){
            update(vehicle);
        }else{
            addToDb(vehicle);
        }
    }

    private static void addToDb(Vehicle vehicle) throws SQLException {
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("INSERT INTO vehicles (model, brand, produced, registration, next_inspection, customer_id) VALUES (?,?,?,?,?,?);",new String[]{"id"});
        sql.setString(1,vehicle.getModel());
        sql.setString(2,vehicle.getBrand());
        sql.setInt(3,vehicle.getProduced());
        sql.setString(4,vehicle.getRegistration());
        sql.setDate(5,Date.valueOf(vehicle.getNextInspection()));
        sql.setInt(6,vehicle.getCustomerId());
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if(rs.next()){
            vehicle.setId(rs.getInt("id"));
        }

    }

    private static void update(Vehicle vehicle) throws SQLException {
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("");
        sql.executeUpdate();
    }


}