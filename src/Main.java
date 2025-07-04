import models.*;
import services.CheckoutService;
import services.impl.CheckoutServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // add some products
        Product chesse = new ExpirableShippableProduct("Chesse", 50, 50, 10, LocalDate.of(2025, 7, 20));
        Product tv = new ShippableProduct("Tv", 1000, 30, 1000);
        Product scratchCard = new Product("Scratch Card", 30, 20);
        Product onlineCourse = new ExpirableProduct("Online Java Course", 200, 10, LocalDate.of(2025, 7, 31));

        final CheckoutService checkoutService = new CheckoutServiceImpl();



        // first test --> balance needed more than amount in the custmoer balance
        Customer customer = new Customer("Abdelatty", 500);
        customer.addItemToCart(chesse, 5);
        customer.addItemToCart(tv, 1);
        customer.addItemToCart(onlineCourse, 1);
        customer.addItemToCart(scratchCard, 2);

        System.out.println("\n\n==== Test Case 1: insufficient balance ====");
        try {
            checkoutService.checkout(customer);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // second test case --> good test
        System.out.println("\n\n==== Test Case 2: valid checkout after balance added ====");
        customer.addBalance(2000);
        try {
            checkoutService.checkout(customer);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // third test case --> add expired product
        System.out.println("\n\n==== Test Case 3: expired product in cart ====");
        Customer customer2 = new Customer("Ali", 1000);
        Product expiredCheese = new ExpirableShippableProduct("Expired Cheese", 50, 10, 0.5, LocalDate.of(2025, 6, 20));
        try {
            customer2.addItemToCart(expiredCheese, 2);
            customer2.addItemToCart(scratchCard, 1);
            checkoutService.checkout(customer2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // fourth test case --> quantity more than stock
        System.out.println("\n\n==== Test Case 4: quantity exceeds stock ====");
        Customer customer3 = new Customer("Omar", 5000);

        try {
            customer3.addItemToCart(tv, 100);
            checkoutService.checkout(customer3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // fifth test case --> empty cart
        System.out.println("\n\n==== Test Case 5: empty cart ====");
        Customer customer4 = new Customer("Sara", 1000);
        try {
            checkoutService.checkout(customer4);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
