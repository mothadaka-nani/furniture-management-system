package users;

import java.util.List;

import models.Furniture;

public class Admin extends User{

    public Admin(int id,String username,String password){
        super(id, username,password);
    }

    public void addProduct(List<Furniture> products,Furniture f){
        products.add(f);
        System.out.println("Product added by Admin");
    }
}