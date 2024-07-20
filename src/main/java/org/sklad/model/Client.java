package org.sklad.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Client implements Serializable {

    public int id;
    public String name;
    public String phone;
    public String address;
    public String password;
    public ArrayList<ClientOrder> clientOrders = new ArrayList<>();
    public ArrayList<Product> cart = new ArrayList<>();


    public Client(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Client(String name, String password, String address) {
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public boolean isEqualTo(Client anotherClient, boolean usingAddress) {
        if (!(Objects.equals(name, anotherClient.name)))
            return false;
        if (usingAddress && !(Objects.equals(address, anotherClient.address)))
            return false;
        if (!usingAddress && !(Objects.equals(password, anotherClient.password)))
            return false;
        return true;
    }

    public void addToCart(Product product){
        cart.add(product);
    }

    public void updateData(Client client){
        this.name = client.name;
        this.address = client.address;
        this.password = client.password;
        this.phone = client.phone;
        this.cart = new ArrayList<>(client.cart);
        this.clientOrders = new ArrayList<>(client.clientOrders);
    }
    public void updateData(ArrayList<String> newData){
        this.name = newData.get(0);
        this.phone = newData.get(1);
        this.address = newData.get(2);
    }

}
