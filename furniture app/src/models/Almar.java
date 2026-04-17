package models;

public class Almar extends Furniture{

    public Almar(String name,double price){
        super(name,price);
    }
    public String getType() {
        return "Almar";
    }
}