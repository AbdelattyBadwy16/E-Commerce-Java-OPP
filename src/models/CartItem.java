package models;

public class CartItem{
    private Product product;
    private int quantity;

    public CartItem(Product product,int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
