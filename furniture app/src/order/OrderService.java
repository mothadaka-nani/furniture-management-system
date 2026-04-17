package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cart.Cart;
import services.DBConnection;

public class OrderService {

    public void placeOrder(Cart cart){

        System.out.println("Order Summary");
        cart.showCart();
        System.out.println("Order Placed Successfully!");
    }
    
    public void placeOrder(int userId) {

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false); // transaction start

            // 1. Calculate total
            String totalSql = "SELECT SUM(p.price * c.quantity) as total " +
                              "FROM cart c JOIN products p ON c.product_id = p.id " +
                              "WHERE c.user_id=?";

            PreparedStatement ps1 = con.prepareStatement(totalSql);
            ps1.setInt(1, userId);
            ResultSet rs = ps1.executeQuery();

            double total = 0;
            if (rs.next()) {
                total = rs.getDouble("total");
            }

            if (total == 0) {
                System.out.println("Cart is empty!");
                return;
            }

            // 2. Insert into orders
            String orderSql = "INSERT INTO orders(user_id, total) VALUES (?, ?)";
            PreparedStatement ps2 = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

            ps2.setInt(1, userId);
            ps2.setDouble(2, total);
            ps2.executeUpdate();

            ResultSet rs2 = ps2.getGeneratedKeys();
            int orderId = 0;

            if (rs2.next()) {
                orderId = rs2.getInt(1);
            }

            // 3. Insert into order_items
            String itemSql = "INSERT INTO order_items(order_id, product_id, quantity) " +
                             "SELECT ?, product_id, quantity FROM cart WHERE user_id=?";

            PreparedStatement ps3 = con.prepareStatement(itemSql);
            ps3.setInt(1, orderId);
            ps3.setInt(2, userId);
            ps3.executeUpdate();

            // 4. Clear cart
            String clearSql = "DELETE FROM cart WHERE user_id=?";
            PreparedStatement ps4 = con.prepareStatement(clearSql);
            ps4.setInt(1, userId);
            ps4.executeUpdate();

            con.commit();

            System.out.println("Order placed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}