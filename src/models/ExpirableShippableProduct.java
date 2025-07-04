package models;


import interfaces.Expirable;
import interfaces.Shippable;

import java.time.LocalDate;

public class ExpirableShippableProduct extends Product implements Expirable, Shippable {
    private double weight;
    private LocalDate expirationDate;

    public ExpirableShippableProduct(String name,double price,int quantity,double weight,LocalDate expirationDate){
        super(name,price,quantity);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
