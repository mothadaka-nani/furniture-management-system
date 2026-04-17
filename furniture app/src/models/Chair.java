package models;

public class Chair extends Furniture{

    public Chair(String name,double price){
        super(name,price);
    }
    public String getType() {
        return "Chair";
    }
}