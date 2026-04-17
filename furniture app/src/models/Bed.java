package models;

public class Bed extends Furniture{

    public Bed(String name,double price){
        super(name,price);
    }
    public String getType() {
        return "Bed";
    }
}