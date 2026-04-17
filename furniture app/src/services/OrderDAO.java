package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderDAO {

    public void viewAllOrders() {
        String sql = "SELECT o.id, u.username, o.total, o.order_date " +
                     "FROM orders o JOIN users u ON o.user_id = u.id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    "Order ID: " + rs.getInt("id") +
                    " | User: " + rs.getString("username") +
                    " | Total: ₹" + rs.getDouble("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 👉 ADD YOUR METHOD HERE
    public void viewOrderItems(int orderId) {

        String sql = "SELECT p.name, oi.quantity " +
                     "FROM order_items oi " +
                     "JOIN products p ON oi.product_id = p.id " +
                     "WHERE oi.order_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getString("name") +
                    " x " + rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}