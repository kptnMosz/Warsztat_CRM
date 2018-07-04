package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class VehicleDao {

    public static ArrayList<Vehicle> loadAll() throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement("SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles;");

            list = loadFromDb(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Vehicle> loadAll(int limit) throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn()) {
            String querry = "SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles ORDER BY next_inspection DESC LIMIT ?";
            PreparedStatement sql = conn.prepareStatement(querry);
            sql.setInt(1,limit);
            list = loadFromDb(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public static ArrayList<Vehicle> loadByCustomer(int custId) throws SQLException {
        ArrayList<Vehicle> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConn()) {
            String querry = "SELECT id, model , brand, produced, registration, next_inspection, customer_id FROM vehicles WHERE customer_id =?";
            PreparedStatement sql = conn.prepareStatement(querry);
            sql.setInt(1, custId);
            list = loadFromDb(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    private static ArrayList<Vehicle> loadFromDb(PreparedStatement sql) throws SQLException {
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
            list.add(new Vehicle(id, model, brand, produced, registration, nextInspection, customerId));
        }
        return list;
    }

    public static Vehicle loadById(int id) throws SQLException {
        Vehicle vehicle = null;
        try (Connection conn = DbUtil.getConn()) {
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
        }
        return vehicle;
    }

    public static void saveToDb(Vehicle vehicle) throws SQLException {
        if (vehicle == null) {
            throw new NullPointerException("Not possible to save a vehicle that does not exist");
        }
        if (vehicle.getId() != 0) {
            update(vehicle);
        } else {
            addToDb(vehicle);
        }
    }

    private static void addToDb(Vehicle vehicle) throws SQLException {

        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("INSERT INTO vehicles (model, brand, produced, registration, next_inspection, customer_id) VALUES (?,?,?,?,?,?);", new String[]{"id"});
        sql.setString(1, vehicle.getModel());
        sql.setString(2, vehicle.getBrand());
        sql.setInt(3, vehicle.getProduced());
        sql.setString(4, vehicle.getRegistration());
        sql.setDate(5, vehicle.getNextInspectionInSqlFormat());
        sql.setInt(6, vehicle.getCustomerId());
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if (rs.next()) {
            vehicle.setId(rs.getInt(1));
        }
        conn.close();

    }

    private static void update(Vehicle vehicle) throws SQLException {
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement("UPDATE vehicles SET model =? , brand =? , produced =? , registration =? , next_inspection =? , customer_id =? WHERE id=?");
        sql.setString(1, vehicle.getModel());
        sql.setString(2, vehicle.getBrand());
        sql.setInt(3, vehicle.getProduced());
        sql.setString(4, vehicle.getRegistration());
        sql.setDate(5, vehicle.getNextInspectionInSqlFormat());
        sql.setInt(6, vehicle.getCustomerId());
        sql.setInt(7, vehicle.getId());
        sql.executeUpdate();
        conn.close();
    }

    public static void clearCustomer(Vehicle vehicle) throws SQLException {
        if(vehicle==null){
            throw new NullPointerException("Not possible to clear a vehicle that does not exist");
        }
        if(vehicle.getId()!=0){
            try(Connection conn = DbUtil.getConn()){
                PreparedStatement sql = conn.prepareStatement("UPDATE vehicles SET customer_id = null WHERE id = ?;");
                sql.setInt(1,vehicle.getId());
                sql.executeUpdate();
                vehicle.setId(0);
            }
        }
    }

    public static void delete(Vehicle vehicle) throws SQLException {
        if(vehicle==null){
            throw new NullPointerException("Not possible to delete a vehicle that does not exist");
        }
        if(vehicle.getId()!=0){
            try(Connection conn = DbUtil.getConn()){
                PreparedStatement sql = conn.prepareStatement("DELETE FROM vehicles WHERE id = ?;");
                sql.setInt(1,vehicle.getId());
                sql.executeUpdate();
                vehicle.setId(0);
            }
        }
    }

}