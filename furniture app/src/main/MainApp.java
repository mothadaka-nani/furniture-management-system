package main;

import java.util.Scanner;

import models.Almar;
import models.Bed;
import models.Chair;
import models.Furniture;
import models.Locker;
import models.Sofa;
import order.OrderService;
import services.AuthService;
import services.CartDAO;
import services.OrderDAO;
import services.ProductService;
import services.UserDAO;
import users.Admin;
import users.Customer;
import users.User;

public class MainApp {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        AuthService auth = new AuthService();
        ProductService ps = new ProductService();
        CartDAO cartDAO = new CartDAO();
        OrderService orderService = new OrderService();
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();

        // 🔐 LOGIN
        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        User user = auth.login(username, password);

        if(user == null){
            System.out.println("Invalid login!");
            return;
        }

        // ================= ADMIN =================
        if(user instanceof Admin){

            while(true)
            {
                System.out.println("\nADMIN PANEL");
                System.out.println("1 Add Product");
                System.out.println("2 View Products");
                System.out.println("3 Delete Product");
                System.out.println("4 View Customers");
                System.out.println("5 View Orders");
                System.out.println("6 Exit");

                int choice = sc.nextInt();
                sc.nextLine();

                switch(choice)
                {
                    case 1:
                        System.out.println("1 Chair 2 Sofa 3 Bed 4 Locker 5 Almar");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter product name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter price: ");
                        double price = sc.nextDouble();

                        Furniture f = null;

                        if(type==1) f = new Chair(name,price);
                        else if(type==2) f = new Sofa(name,price);
                        else if(type==3) f = new Bed(name,price);
                        else if(type==4) f = new Locker(name,price);
                        else if(type==5) f = new Almar(name,price);

                        if(f!=null){
                            ps.addProduct(f);
                            System.out.println("Product Added");
                        }
                        break;

                    case 2:
                        ps.showProducts();
                        break;

                    case 3:
                        ps.showProducts();
                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        ps.deleteProduct(id);
                        break;

                    case 4:
                        userDAO.viewUsers();
                        break;

                    case 5:
                        orderDAO.viewAllOrders();
                        System.out.print("Enter Order ID: ");
                        int orderId = sc.nextInt();
                        orderDAO.viewOrderItems(orderId);
                        break;

                    case 6:
                        return;
                }
            }
        }

        // ================= CUSTOMER =================
        else if(user instanceof Customer){
        	System.out.println("\nCustomer PANEL");
            while(true)
            {
            	System.out.println("\n1 View Products");
            	System.out.println("2 Add Product to Cart");
            	System.out.println("3 View Cart");
            	System.out.println("4 Remove Product from Cart");
            	System.out.println("5 Place Order");
            	System.out.println("6 Exit");

                int choice = sc.nextInt();

                switch(choice)
                {
                    case 1:
                        ps.showProducts();
                        break;

                    case 2:
                        while(true){
                            ps.showProducts();
                            System.out.print("Enter Product ID (-1 to stop): ");
                            int pid = sc.nextInt();

                            if(pid == -1) break;

                            cartDAO.addToCart(user.getId(), pid);
                        }
                        break;

                    case 3:
                        cartDAO.viewCart(user.getId());
                        break;

                    case 4:
                        cartDAO.viewCart(user.getId());
                        System.out.print("Enter Product ID to remove: ");
                        int removeId = sc.nextInt();
                        cartDAO.removeFromCart(user.getId(), removeId);
                        break;

                    case 5:
                        orderService.placeOrder(user.getId());
                        break;

                    case 6:
                        System.exit(0);
                }
            }
        }
    }
}