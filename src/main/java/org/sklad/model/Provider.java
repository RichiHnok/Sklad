package org.sklad.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Provider implements Serializable {
    private int id;
    private String name;
    private String phone;
    private ArrayList<Product> products;
    private ArrayList<Product> cart;
    private static int counter = 0;

    public Provider(
            String name,
            String phone
    ) {
        this.id = provideId();
        this.name = name;
        this.phone = phone;
        this.products = new ArrayList<>();
        cart = new ArrayList<>();
    }

    // -------------------------------------------------------- Getters Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    // -------------------------------------------------------- Products
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    // -------------------------------------------------------- Cart
    public void addToCart(Product product){
        cart.add(product);
    }

    public ArrayList<Product> getCart(){
        return cart;
    }

    public void removeFromCart(Product product){
        cart.remove(product);
    }

    public void clearCart(){
        cart = new ArrayList<>();
    }

    // -------------------------------------------------------- Private Functions
    private static int provideId(){
        return counter++;
    }

    @Override
    public String toString() {
        return name;
    }
}
