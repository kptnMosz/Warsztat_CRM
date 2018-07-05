package pl.coderslab.dao;

import java.sql.*;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static String LOAD_ALL_ORDERS = "SELECT" +
            "  id," +
            "  acceptance," +
            "  planned_fix," +
            "  start_fix," +
            "  problem_desc," +
            "  fix_desc," +
            "  status_id," +
            "  vehicle_id," +
            "  price," +
            "  parts_cost," +
            "  labor_cost," +
            "  workhours," +
            "  employee_id" +
            "FROM orders";
    private static String loadOrderById = "SELECT" +
            "  id," +
            "  acceptance," +
            "  planned_fix," +
            "  start_fix," +
            "  problem_desc," +
            "  fix_desc," +
            "  status_id," +
            "  vehicle_id," +
            "  price," +
            "  parts_cost," +
            "  labor_cost," +
            "  workhours," +
            "  employee_id" +
            " FROM orders" +
            " WHERE id = ?";
    private static String LOAD_ALL_BY_STATUS = "SELECT" +
            "  id," +
            "  acceptance," +
            "  planned_fix," +
            "  start_fix," +
            "  problem_desc," +
            "  fix_desc," +
            "  status_id," +
            "  vehicle_id," +
            "  price," +
            "  parts_cost," +
            "  labor_cost," +
            "  workhours," +
            "  employee_id" +
            " FROM orders" +
            " WHERE status_id = ?";


    private static String save = "INSERT INTO orders(acceptance, planned_fix,start_fix,problem_desc,fix_desc,status_id, vehicle_id,price,parts_cost,labor_cost,workhours,employee_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String updateById = "UPDATE orders SET acceptance=?, planned_fix=?, start_fix=?, problem_desc=?, fix_desc=?, status_id=?, vehicle_id=?, price=?, parts_cost=?, labor_cost=?, workhours=?, employee_id=?  WHERE id=?";
    private static String deleteById = "DELETE FROM orders where id=?";


    public static void saveOrUpdate(Order order) {

        if (order.getId() == 0) {
            try (Connection conn = DbUtil.getConn()) {
                String[] createColumn = {"id"};
                PreparedStatement sql = conn.prepareStatement(save, createColumn);
                sql.setDate(1, order.getAcceptanceToRepair());
                sql.setDate(2, order.getPlannedFix());
                sql.setDate(3, order.getStartFix());
                sql.setString(4, order.getProblemDesc());
                sql.setString(5, order.getFixDesc());
                sql.setInt(6, order.getStatusId());
                sql.setInt(7, order.getRepairedVehicleId());
                sql.setBigDecimal(8, order.getPrice());
                sql.setBigDecimal(9, order.getPartsCost());
                sql.setBigDecimal(10, order.getLaborCost());
                sql.setInt(11, order.getWorkhours());
                sql.setInt(12, order.getEmployeeId());

                sql.executeUpdate();
                ResultSet rs = sql.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (Connection conn = DbUtil.getConn()) {
                PreparedStatement sql = conn.prepareStatement(updateById);
                sql.setDate(1, order.getAcceptanceToRepair());
                sql.setDate(2, order.getPlannedFix());
                sql.setDate(3, order.getStartFix());
                sql.setString(4, order.getProblemDesc());
                sql.setString(5, order.getFixDesc());
                sql.setInt(6, order.getStatusId());
                sql.setInt(7, order.getRepairedVehicleId());
                sql.setBigDecimal(8, order.getPrice());
                sql.setBigDecimal(9, order.getPartsCost());
                sql.setBigDecimal(10, order.getLaborCost());
                sql.setInt(11, order.getWorkhours());
                sql.setInt(12, order.getEmployeeId());
                sql.setInt(13, order.getId());
                sql.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public static void delete(int id) {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(deleteById);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie powiodlo się usuwanie zamówienia");
        }
    }

    public static Order loadById(int id) {
        Order order = new Order();
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(loadOrderById);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setAcceptanceToRepair(rs.getDate("acceptanceToRepair"));
                order.setPlannedFix(rs.getDate("plannedFix"));
                order.setStartFix(rs.getDate("startFix"));
                order.setProblemDesc(rs.getString("problemDesc"));
                order.setFixDesc(rs.getString("fixDesc"));
                order.setStatusId(rs.getInt("statusId"));
                order.setRepairedVehicleId(rs.getInt("repairedVehicleId"));
                order.setPrice(rs.getBigDecimal("price"));
                order.setPartsCost(rs.getBigDecimal("partsCost"));
                order.setLaborCost(rs.getBigDecimal("laborCost"));
                order.setWorkhours(rs.getInt("workhours"));
                order.setEmployeeId(rs.getInt("employeeId"));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("wystąpił błąd");
        }
        return null;
    }

    public static List<Order> loadAll() {
        return loadList(LOAD_ALL_ORDERS);
    }


    public static List<Order> loadAllActualOrders() {
        return loadList(LOAD_ALL_BY_STATUS.replaceAll("\\?", "3"));

    }

    private static List<Order> loadList(String querry) {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(querry);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Order orderNext = new Order();
                orderNext.setId(rs.getInt("id"));
                orderNext.setAcceptanceToRepair(rs.getDate("acceptance"));
                orderNext.setPlannedFix(rs.getDate("planned_fix"));
                orderNext.setStartFix(rs.getDate("start_fix"));
                orderNext.setProblemDesc(rs.getString("problem_desc"));
                orderNext.setFixDesc(rs.getString("fix_desc"));
                orderNext.setStatusId(rs.getInt("status_id"));
                orderNext.setRepairedVehicleId(rs.getInt("vehicle_id"));
                orderNext.setPrice(rs.getBigDecimal("price"));
                orderNext.setPartsCost(rs.getBigDecimal("parts_cost"));
                orderNext.setLaborCost(rs.getBigDecimal("labor_cost"));
                orderNext.setWorkhours(rs.getInt("workhours"));
                orderNext.setEmployeeId(rs.getInt("employee_id"));
                orders.add(orderNext);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("bład ponownie");
        }
        return orders;
    }


}
