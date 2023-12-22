package org.sklad.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientOrder implements Serializable {
    private int id;
    public String deliveryName;
    public String deliveryPhone;
    public String deliveryAddress;
    public String deliveryDate;
    public OrderStatus deliveryStatus;
    public ArrayList<Product> deliveryProducts;

    private static int counter = 0;

    public ClientOrder(
            String name,
            String phone,
            String address,
            String date,
            OrderStatus status,
            ArrayList<Product> products
            ) {
        deliveryName = name;
        deliveryPhone = phone;
        deliveryAddress = address;
        deliveryDate = date;
        deliveryStatus = status;
        deliveryProducts = new ArrayList<>(products);
    }
    public ClientOrder(
            ClientOrder anotherClientOrder
    ) {
        deliveryName = anotherClientOrder.deliveryName;
        deliveryPhone = anotherClientOrder.deliveryPhone;
        deliveryAddress = anotherClientOrder.deliveryAddress;
        deliveryDate = anotherClientOrder.deliveryDate;
        deliveryStatus = anotherClientOrder.deliveryStatus;
        deliveryProducts = new ArrayList<>(anotherClientOrder.deliveryProducts);
        id = anotherClientOrder.getId();
    }

    public double calculateTotalPrice(){
        double sum = 0;
        for (Product product: deliveryProducts) {
//            sum += product.calculateTotalPrice();
            sum += product.availableAmount * product.pricePerPiece;
        }
        return sum;
    }

    public boolean checkValidity(){
        if (deliveryName == null || deliveryName.isBlank())
            return false;
        if (deliveryPhone == null || deliveryPhone.isBlank())
            return false;
        if (deliveryAddress == null || deliveryAddress.isBlank())
            return false;
        if (deliveryDate == null || deliveryDate.isBlank())
            return false;
        return true;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public static int provideId(){
        counter++;
        return counter;
    }
}
