package models;

public class Sofa extends Furniture{

    public Sofa(String name,double price){
        super(name,price);
    }
    public String getType() {
        return "Sofa";
    }
}