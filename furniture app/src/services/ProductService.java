package services;

import java.util.List;

import models.Furniture;

public class ProductService {

    private ProductDAO dao;

    public ProductService() {
        dao = new ProductDAO();
    }

    public void addProduct(Furniture f) {
        dao.addProduct(f);
    }

    public void showProducts() {
        List<Furniture> list = dao.getAllProducts();

        for (Furniture f : list) {
            System.out.println(f.getId() + " - " + f.getName() + " - " + f.getPrice());
        }
    }
    public void deleteProduct(int id) {
        dao.deleteProduct(id);
    }
    public Furniture getProduct(int index) {
        List<Furniture> list = dao.getAllProducts();

        if (index > 0 && index <= list.size()) {
            return list.get(index - 1);
        }
        return null;
    }
}