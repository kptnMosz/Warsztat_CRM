package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private String loadAllEmployees = "SELECT * FROM employees";
    private String saveEmployee = "INSERT INTO employees(name, surname, adress, phone, notes, wage) VALUES (?,?,?,?,?,?)";
    private String updateEmployee = "UPDATE employees SET name=?,surname=?,adress=?,phone=?,notes=?,wage=? WHERE id=?";
    private String deleteEmployee = "DELETE FROM employees WHERE id=?";

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
                worker.setWage(rs.getDouble("wage"));
                employees.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nieprawidłowe dane,   ");
        }
        System.out.println("pracownik=  " + employees);//test print
        return employees;
    }

    public void saveOrUpdateToDb(int id, String name, String surname, String adress, String phone, String notes, double wage) {
        if (id == 0) {//sprawdzenie czy istnieje pracownik o podanym ID

            try (Connection conn = DbUtil.getConn()) {//połączenie z DB
                String[] employeeColumn = {"id"};
                PreparedStatement sql = conn.prepareStatement(saveEmployee, employeeColumn);
                sql.setString(1, name);
                sql.setString(2, surname);
                sql.setString(3, adress);
                sql.setString(4, phone);
                sql.setString(5, notes);
                sql.setDouble(6, wage);
                sql.executeUpdate();
                ResultSet rs = sql.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {//jeżeli nie ma pracownika o podanym wczesniej ID to rozpoczyna dodawanie
            try (Connection conn = DbUtil.getConn()) {
                PreparedStatement sql = conn.prepareStatement(updateEmployee);
                sql.setString(1, name);
                sql.setString(2, surname);
                sql.setString(3, adress);
                sql.setString(4, phone);
                sql.setString(5, notes);
                sql.setDouble(6, wage);
                sql.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteWorker(int id) {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement sql = conn.prepareStatement(deleteEmployee);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Pracownik pozostał na liście przez błąd podczas usuwania");
        }
    }

}