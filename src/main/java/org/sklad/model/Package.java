package org.sklad.model;

import java.io.Serializable;

public class Package implements Serializable {

    private ClientOrder packageClientOrder = null;
    private String clientAddress = null;
    private String deliveryDate = null;
    private String currentAddress = null;

    // Placeholder
    public Package(){
        packageClientOrder = null;
        clientAddress = new String("default address");
        deliveryDate = new String("29.12.2000");
        currentAddress = "Warehouse";
    }

    public Package(ClientOrder clientOrder){
        packageClientOrder = new ClientOrder(clientOrder);
        clientAddress = packageClientOrder.deliveryAddress;
        deliveryDate = packageClientOrder.deliveryDate;
        currentAddress = "Warehouse";
    }

    public ClientOrder getPackageOrder(){
        return packageClientOrder;
    }
    public int getOderId(){
        return packageClientOrder.getId();
    }
    public String getClientAddress(){
        return clientAddress;
    }
    public String getCurrentAddress(){
        return currentAddress;
    }
    public String getDeliveryDate(){
        return deliveryDate;
    }
    public void setCurrentAddress(String address){
        currentAddress = address;
    }
}