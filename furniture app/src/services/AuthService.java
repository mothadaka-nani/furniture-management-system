package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import users.Admin;
import users.Customer;
import users.User;

public class AuthService {

    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int id = rs.getInt("id");

                if (role.equalsIgnoreCase("ADMIN"))
                    return new Admin(id, username, password);
                else
                    return new Customer(id, username, password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}