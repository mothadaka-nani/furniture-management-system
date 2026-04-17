package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDAO {
	public void removeFromCart(int userId, int productId) {

	    String check = "SELECT quantity FROM cart WHERE user_id=? AND product_id=?";
	    String decrease = "UPDATE cart SET quantity = quantity - 1 WHERE user_id=? AND product_id=?";
	    String delete = "DELETE FROM cart WHERE user_id=? AND product_id=?";

	    try (Connection con = DBConnection.getConnection()) {

	        PreparedStatement ps1 = con.prepareStatement(check);
	        ps1.setInt(1, userId);
	        ps1.setInt(2, productId);

	        ResultSet rs = ps1.executeQuery();

	        if (rs.next()) {
	            int qty = rs.getInt("quantity");

	            if (qty > 1) {
	                PreparedStatement ps2 = con.prepareStatement(decrease);
	                ps2.setInt(1, userId);
	                ps2.setInt(2, productId);
	                ps2.executeUpdate();
	                System.out.println("Quantity reduced");
	            } else {
	                PreparedStatement ps3 = con.prepareStatement(delete);
	                ps3.setInt(1, userId);
	                ps3.setInt(2, productId);
	                ps3.executeUpdate();
	                System.out.println("Product removed from cart");
	            }
	        } else {
	            System.out.println("Product not found in cart");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void addToCart(int userId, int productId) {

	    String check = "SELECT * FROM cart WHERE user_id=? AND product_id=?";
	    String insert = "INSERT INTO cart(user_id, product_id, quantity) VALUES (?, ?, 1)";
	    String update = "UPDATE cart SET quantity = quantity + 1 WHERE user_id=? AND product_id=?";

	    try (Connection con = DBConnection.getConnection()) {

	        PreparedStatement ps1 = con.prepareStatement(check);
	        ps1.setInt(1, userId);
	        ps1.setInt(2, productId);

	        ResultSet rs = ps1.executeQuery();

	        if (rs.next()) {
	            PreparedStatement ps2 = con.prepareStatement(update);
	            ps2.setInt(1, userId);
	            ps2.setInt(2, productId);
	            ps2.executeUpdate();
	        } else {
	            PreparedStatement ps3 = con.prepareStatement(insert);
	            ps3.setInt(1, userId);
	            ps3.setInt(2, productId);
	            ps3.executeUpdate();
	        }

	        System.out.println("Added to cart");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void viewCart(int userId) {

	    String sql = "SELECT p.id, p.name, p.price, c.quantity " +
	                 "FROM cart c JOIN products p ON c.product_id = p.id " +
	                 "WHERE c.user_id=?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        double total = 0;

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            double price = rs.getDouble("price");
	            int qty = rs.getInt("quantity");

	            System.out.println(id + " - " + name + " x" + qty + " - ₹" + price);
	            total += price * qty;
	        }

	        System.out.println("Total = ₹" + total);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}