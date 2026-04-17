package models;

import interfaces.Product;

public abstract class Furniture implements Product {
	protected int id;
    protected String name;
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    protected double price;

    public Furniture(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    @Override
    public void display() {
        System.out.println(getType() + " - " + name + " - ₹" + price);
    }
}