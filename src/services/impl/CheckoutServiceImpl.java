package services.impl;

import interfaces.Expirable;
import interfaces.Shippable;
import models.Cart;
import models.CartItem;
import models.Customer;
import models.Product;
import services.CheckoutService;
import services.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class CheckoutServiceImpl implements CheckoutService {

    final ShippingService shippingService = new ShippingServiceImpl();

    @Override
    public void checkout(Customer customer) {
        if(customer == null){
            throw new RuntimeException("Customer is required to proceed with checkout.");
        }

        Cart cart = customer.getCart();

        if(cart.isEmpty()){
            throw new RuntimeException("Cart is empty.");
        }

        double subTotal = 0;
        double total = 0;
        double fee = 50;
        double totalWeight = 0;
        List<String> shipmentDocs = new ArrayList<>();
        List<String> itemsDocs = new ArrayList<>();
        List<Shippable> shippableItems = new ArrayList<>();

        for(CartItem item : cart.getItems()){
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            if (product instanceof Expirable expirable && expirable.isExpired()) {
                System.out.println(product.getName() + " is expired on " + expirable.getExpirationDate());
                continue;
            }

            if(quantity > product.getQuantity()){
                System.out.println("Cannot add " + quantity + " of " + product.getName() + ". Only " + product.getQuantity() + " in stock.");
                continue;
            }

            if (product instanceof Shippable shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add(shippable);
                }
                shipmentDocs.add(quantity + "x " + product.getName() + "     " + (quantity * ((Shippable) product).getWeight()) + "g");
                totalWeight += shippable.getWeight() * quantity;
            }
            itemsDocs.add(quantity + "x " + product.getName() + "     " + product.getPrice() * quantity);
            product.reduceQuantity(quantity);
            subTotal += (product.getPrice() * quantity);
        }

        total = subTotal + (shippableItems.isEmpty() ? 0 :(int) fee)    ;
        if(total > customer.getBalance()){
            throw new RuntimeException("Insufficient balance. Required: " + total + " and your balance is : " + customer.getBalance());
        }

        try{
            customer.reduceBalance(total);
        }catch (Exception ex){
            System.out.println(ex);
        }

        shippingService.ship(shippableItems);

        System.out.println("** Checkout receipt **");
        for (String line : itemsDocs) {
            System.out.println(line);
        }

        System.out.println("----------------------");
        System.out.println("Subtotal " + subTotal);
        System.out.println("Shipping fees " + (shippableItems.isEmpty() ? 0 : fee));
        System.out.println("Amount " + total);
        System.out.println("Customer Current Balance  " + customer.getBalance());

    }


}
