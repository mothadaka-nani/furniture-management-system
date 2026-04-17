package cart;

import interfaces.Product;
import java.util.*;

public class Cart {

    List<Product> items = new ArrayList<>();

    public void addItem(Product p){
        items.add(p);
        System.out.println(p.getName()+" added to cart");
    }

    public void showCart(){

        double total=0;

        for(Product p:items){
            p.display();
            total+=p.getPrice();
        }

        System.out.println("Total = ₹"+total);
    }
}