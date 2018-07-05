package pl.coderslab.dao;
import java.sql.*;

import com.sun.org.apache.xpath.internal.operations.Or;
import pl.coderslab.DbUtil;
import pl.coderslab.model.Order;
import sun.util.calendar.LocalGregorianCalendar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static String LoadAllOrders = "SELECT * FROM orders";
    private static String loadOrderById = "SELECT * FROM orders WHERE id=?";
    //@todo w przyszlosci jak juz beda statusy:  private static String LOAD_ALL_ACTUAL_ORDERS = "SELECT * FROM orders WHERE orders.status LIKE '%naprawie%'";
    private static String save = "INSERT INTO orders(id,acceptance, planned_fix,start_fix,problem_desc,fix_desc,status_id, vehicle_id,price,parts_cost,labor_cost,workhours,employee_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String updateById = "UPDATE orders SET acceptance=?, planned_fix=?, start_fix=?, problem_desc=?, fix_desc=?, status_id=?, vehicle_id=?, price=?, parts_cost=?, labor_cost=?, workhours=?, employee_id=?  WHERE id=?";
    private static String deleteById = "DELETE FROM orders where id=?";


    public void saveOrUpdate(int id, Date acceptanceToRepair, Date plannedFix, Date startFix, int employeeId, String problemDesc, String fixDesc, int statusId, int repairedVehicleId, BigDecimal price, BigDecimal partsCost, BigDecimal laborCost, int workhours) {

        if (id == 0) {
            try (Connection conn = DbUtil.getConn()) {
                String[] createColumn = {"id"};
                PreparedStatement sql = conn.prepareStatement(save);
                sql.setDate(1, acceptanceToRepair);
                sql.setDate(2, plannedFix);
                sql.setDate(3, startFix);
                sql.setString(4, problemDesc);
                sql.setString(5, fixDesc);
                sql.setInt(6, statusId);
                sql.setInt(7, repairedVehicleId);
                sql.setBigDecimal(8, price);
                sql.setBigDecimal(9, partsCost);
                sql.setBigDecimal(10, laborCost);
                sql.setInt(11, workhours);
                sql.setInt(12, employeeId);

                sql.executeUpdate();
                ResultSet rs = sql.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (Connection conn = DbUtil.getConn()) {
                PreparedStatement sql = conn.prepareStatement(updateById);
                sql.setDate(1, acceptanceToRepair);
                sql.setDate(2, plannedFix);
                sql.setDate(3, startFix);
                sql.setString(4, problemDesc);
                sql.setString(5, fixDesc);
                sql.setInt(6, statusId);
                sql.setInt(7, repairedVehicleId);
                sql.setBigDecimal(8, price);
                sql.setBigDecimal(9, partsCost);
                sql.setBigDecimal(10, laborCost);
                sql.setInt(11, workhours);
                sql.setInt(12, employeeId);
                sql.setInt(13, id);
                sql.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }


    public void detele(int id) {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(deleteById);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie powiodlo się usuwanie zamówienia");
        }
    }

    public Order loadById(int id) {
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
    // TODO public List<Order> loadAllActualOrders() { jak będą statusy     }

    public List<Order> loadAll(){
        List<Order> orders=new ArrayList<>();
        try (Connection conn=DbUtil.getConn()){
            PreparedStatement sql=conn.prepareStatement(LoadAllOrders);
            ResultSet rs= sql.executeQuery();
            while (rs.next()){
                Order orderNext= new Order();
                orderNext.setId(rs.getInt("id"));
                orderNext.setAcceptanceToRepair(rs.getDate("acceptanceToRepair"));
                orderNext.setPlannedFix(rs.getDate("plannedFix"));
                orderNext.setStartFix(rs.getDate("startFix"));
                orderNext.setProblemDesc(rs.getString("problemDesc"));
                orderNext.setFixDesc(rs.getString("fixDesc"));
                orderNext.setStatusId(rs.getInt("statusId"));
                orderNext.setRepairedVehicleId(rs.getInt("repairedVehicleId"));
                orderNext.setPrice(rs.getBigDecimal("price"));
                orderNext.setPartsCost(rs.getBigDecimal("partsCost"));
                orderNext.setLaborCost(rs.getBigDecimal("laborCost"));
                orderNext.setWorkhours(rs.getInt("workhours"));
                orderNext.setEmployeeId(rs.getInt("employeeId"));
                orders.add(orderNext);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("bład ponownie");
        }
        return orders;
    }

}
