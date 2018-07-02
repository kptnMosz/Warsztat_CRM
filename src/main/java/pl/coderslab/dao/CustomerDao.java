package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerDao {

    // non-static DB methods
    //1) Adding a customer to the database

    public void addCustomerToDb (Customer customer) {
        int id = customer.getId();
        String birthdayDateS = customer.getBirthdayDate();
        Date birthdayDate = null;
        try {
            birthdayDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayDateS);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
        if (id == 0) {
            try {
                String generatedColumns[] = {"ID"};
                PreparedStatement stmt = DbUtil.getConn().prepareStatement("INSERT INTO customers(name,surname,birthday) VALUES (?,?,?)", generatedColumns);
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getSurname());
                stmt.setDate(3, new java.sql.Date(birthdayDate.getTime()));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {
                PreparedStatement stmt = DbUtil.getConn().prepareStatement("UPDATE customers SET name=?, surname=?, birthday=? WHERE id=?");
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getSurname());
                stmt.setDate(3, new java.sql.Date(birthdayDate.getTime()));
                stmt.setLong(4, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void delete(Customer customer) {
        String sql = "DELETE FROM customers WHERE id= ?";
        try {
            if (customer. getId() != 0) {
                PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
                stmt.setLong(1, customer.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Customer> loadAll() throws SQLException, ParseException {
        String sql = "SELECT * FROM customers";
        PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
        return getCustomersFromStatement(stmt);
    }

    private static ArrayList<Customer> getCustomersFromStatement(PreparedStatement stmt) {
        try {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Customer loadedCustomer = new Customer();
                String birthdayDateS = loadedCustomer.getBirthdayDate();
                Date birthdayDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayDateS);
                Date birthdayDateParsed = new java.sql.Date(birthdayDate.getTime());
                long id = loadedCustomer.getId();
                String name = loadedCustomer.getName();
                String surname = loadedCustomer.getSurname();

                id= resultSet.getLong("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                birthdayDate = resultSet.getDate("birthday");
                customers.add(loadedCustomer);
            }
            return customers;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
        return null;
    }
}
