package models;

public class Customer{
    private String name;
    private double balance;
    private Cart cart = new Cart();
    public Customer(String name,double balance){
        this.name = name;
        this.balance = balance;
    }

    public void addItemToCart(Product product,int quantity){
        cart.add(product,quantity);
    }

    public Cart getCart(){
        return cart;
    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }

    public void addBalance(double amount){
        if(amount < 0){
            throw new RuntimeException("Amount must be positive.");
        }
        this.balance += amount;
    }

    public void reduceBalance(double amount){
        if(amount > this.balance){
            throw new RuntimeException("Not enough balance.");
        }
        if(amount < 0){
            throw new RuntimeException("Amount must be positive.");
        }
        this.balance -= amount;
    }

}