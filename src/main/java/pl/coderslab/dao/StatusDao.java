package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDao {
 public static String getName(int id) throws SQLException {
     String name="";
     try (Connection conn = DbUtil.getConn()) {
         PreparedStatement sql = conn.prepareStatement("SELECT name FROM statuses WHERE id=" + id + ";");
         ResultSet rs = sql.executeQuery();
         if (rs.next()) {
             name = rs.getString("name");
            }
     }
     return name;
 }
}
