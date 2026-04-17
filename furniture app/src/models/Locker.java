package models;

public class Locker extends Furniture{

    public Locker(String name,double price){
        super(name,price);
    }
    public String getType() {
        return "Locker";
    }
}