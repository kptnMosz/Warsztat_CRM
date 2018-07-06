package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Employee;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static String loadAllEmployees = "SELECT * FROM employees";
    private static String saveEmployee = "INSERT INTO employees(name, surname, adress, phone, notes, wage) VALUES (?,?,?,?,?,?)";
    private static String updateEmployee = "UPDATE employees SET name=?,surname=?,adress=?,phone=?,notes=?,wage=? WHERE id=?";
    private static String deleteEmployee = "DELETE FROM employees WHERE id=?";
    private static String loadedById = "SELECT * FROM employees WHERE id=?";

    //ogólnie jeszcze wybranie pracownika po ID pozostało ale zastanawiam się czy wstawić to tutaj
    // czy też zrobić servlet z takim zachowaniem
    //w tym przypadku nizej umieszczam sql zapytanie
    //private String selectById="SELECT * FROM employees WHERE id=?";

    public List<Employee> loadAll() {

        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DbUtil.getConn()) {

            PreparedStatement sql = conn.prepareStatement(loadAllEmployees);
            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Employee worker = new Employee();
                worker.setId(rs.getInt("id"));
                worker.setName(rs.getString("name"));
                worker.setSurname(rs.getString("surname"));
                worker.setAdress(rs.getString("adress"));
                worker.setPhone(rs.getString("phone"));
                worker.setNotes(rs.getString("notes"));
                worker.setWage(rs.getBigDecimal("wage"));
                employees.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nieprawidłowe dane,   ");
        }
        System.out.println("pracownik=  " + employees);//test print
        return employees;
    }

    public static void saveToDb(Employee employee) throws SQLException {
        if (employee == null) {
            throw new NullPointerException("Not possible to save a employee that does not exist");
        }
        if (employee.getId() != 0) {
            update(employee);
        } else {
            addToDb(employee);
        }
    }

    public static void addToDb(Employee employee) throws SQLException {

        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement(saveEmployee);
        sql.setString(1, employee.getName());
        sql.setString(2, employee.getSurname());
        sql.setString(3, employee.getAdress());
        sql.setString(4, employee.getPhone());
        sql.setString(5, employee.getNotes());
        sql.setBigDecimal(6,employee.getWage());
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if (rs.next()) {
            employee.setId(rs.getInt(1));
        }
        conn.close();
    }

    public static void update(Employee employee) throws SQLException {
        Connection conn = DbUtil.getConn();
        PreparedStatement sql = conn.prepareStatement(updateEmployee);
        sql.setString(1, employee.getName());
        sql.setString(2, employee.getSurname());
        sql.setString(3, employee.getAdress());
        sql.setString(4, employee.getPhone());
        sql.setString(5, employee.getNotes());
        sql.setBigDecimal(6,employee.getWage());
        sql.executeUpdate();
        conn.close();
    }

//    public void saveOrUpdateToDb(int id, String name, String surname, String adress, String phone, String notes, double wage) {
//        if (id == 0) {//sprawdzenie czy istnieje pracownik o podanym ID
//
//            try (Connection conn = DbUtil.getConn()) {//połączenie z DB
//                String[] employeeColumn = {"id"};
//                PreparedStatement sql = conn.prepareStatement(saveEmployee, employeeColumn);
//                sql.setString(1, name);
//                sql.setString(2, surname);
//                sql.setString(3, adress);
//                sql.setString(4, phone);
//                sql.setString(5, notes);
//                sql.setBigDecimal(6, BigDecimal.valueOf(wage));
//                sql.executeUpdate();
//                ResultSet rs = sql.getGeneratedKeys();
//                if (rs.next()) {
//                    id = rs.getInt(1);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {//jeżeli nie ma pracownika o podanym wczesniej ID to rozpoczyna dodawanie
//            try (Connection conn = DbUtil.getConn()) {
//                PreparedStatement sql = conn.prepareStatement(updateEmployee);
//                sql.setString(1, name);
//                sql.setString(2, surname);
//                sql.setString(3, adress);
//                sql.setString(4, phone);
//                sql.setString(5, notes);
//                sql.setDouble(6, wage);
//                sql.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void deleteWorker(int id) {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(deleteEmployee);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Pracownik pozostał na liście przez błąd podczas usuwania");
        }
    }

    public static Employee loadById(int id) throws SQLException {
        Employee employee = null;
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(loadedById);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                String adress = rs.getString("adress");
                String notes = rs.getString("notes");
                BigDecimal wage = rs.getBigDecimal("wage");
                employee = new Employee(id, name, surname, adress, phone, notes, wage);
            }
        }
        return employee;
    }
}
