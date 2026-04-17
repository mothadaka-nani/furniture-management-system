package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Almar;
import models.Bed;
import models.Chair;
import models.Furniture;
import models.Locker;
import models.Sofa;

public class ProductDAO {

    public void addProduct(Furniture f) {
        String sql = "INSERT INTO products(name, price, type) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, f.getName());
            ps.setDouble(2, f.getPrice());
            ps.setString(3, f.getClass().getSimpleName());

            ps.executeUpdate();
            System.out.println("Product saved to DB");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Furniture> getAllProducts() {
        List<Furniture> list = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String type = rs.getString("type");

                int id = rs.getInt("id");
                Furniture f = null;
                if(type.equals("Chair")) f = new Chair(name, price);
                else if(type.equals("Sofa")) f = new Sofa(name, price);
                else if(type.equals("Bed")) f = new Bed(name, price);
                else if(type.equals("Locker")) f = new Locker(name, price);
                else if(type.equals("Almar")) f = new Almar(name, price);

                if(f != null) {
                    f.setId(id);
                    list.add(f);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Product not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}