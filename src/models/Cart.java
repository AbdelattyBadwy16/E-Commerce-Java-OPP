package models;

import java.util.ArrayList;
import java.util.List;

public class Cart{
    private List<CartItem> items;

    public Cart(){
        items = new ArrayList<>();
    }

    public void add(Product product,int quantity){
        if(quantity > product.getQuantity()){
            throw new RuntimeException("Quantity exceeds available products.");
        }
        items.add(new CartItem(product,quantity));
    }

    public List<CartItem> getItems(){
        return items;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }


}