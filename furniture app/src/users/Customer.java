package users;

public class Customer extends User {

    public Customer(int id,String username,String password){
        super(id,username,password);
    }

    public void welcome(){
        System.out.println("Welcome "+username+" to Furniture Store!");
    }

}