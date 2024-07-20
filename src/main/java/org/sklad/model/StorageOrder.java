package org.sklad.model;

import java.io.Serializable;
import java.util.ArrayList;

public class StorageOrder implements Serializable {
    private int id;
    private int providerId;
    private String providerName;
    private String providerPhone;
    private String deliveryDate;
    private OrderStatus status;
    private ArrayList<Product> products;
    private static int counter = 0;

    public StorageOrder(
        Provider provider,
        String deliveryDate,
        ArrayList<Product> products
    ){
        id = provideId();
        providerId = provider.getId();
        providerName = provider.getName();
        providerPhone = provider.getPhone();
        this.deliveryDate = deliveryDate;
        status = OrderStatus.BEING_DELIVERED;
        this.products = accumulateProducts(products);
    }

    // ------------------------------------------------------------ Getters Setters
    public int getId() {
        return id;
    }

    public int getProviderId() {
        return providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // ------------------------------------------------------------ Private Functions
    private ArrayList<Product> accumulateProducts(ArrayList<Product> products){
        ArrayList<Product> accumulatedProducts = new ArrayList<>();
        boolean flag;

        accumulatedProducts.add(new Product(products.get(0), products.get(0).availableAmount));
        for (int i = 1; i < products.size(); i++) {
            flag = false;
            for (int j = 0; j < accumulatedProducts.size(); j++) {
                if (products.get(i).id == accumulatedProducts.get(j).id){
                    flag = true;
                    accumulatedProducts.get(j).availableAmount += products.get(i).availableAmount;
                }
            }
            if (!flag)
                accumulatedProducts.add(new Product(products.get(i), products.get(i).availableAmount));
        }
        return accumulatedProducts;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    private static int provideId(){
        return counter++;
    }
}