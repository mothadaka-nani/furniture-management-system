package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

    public void viewUsers() {
        String sql = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " + 
                    rs.getString("username") + " - " +
                    rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}