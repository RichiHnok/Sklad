package org.sklad.model;

public class Package {

    private Order packageOrder = null;
    private String clientAddress = null;
    private String deliveryDate = null;
    private String currentAddress = null;

    // Placeholder
    public Package(){
        packageOrder = null;
        clientAddress = new String("default address");
        deliveryDate = new String("29.12.2000");
        currentAddress = "Warehouse";
    }

    public Package(Order order){
        packageOrder = new Order(order);
        clientAddress = packageOrder.deliveryAddress;
        deliveryDate = packageOrder.deliveryDate;
        currentAddress = "Warehouse";
    }

    public Order getPackageOrder(){
        return packageOrder;
    }
    public int getOderId(){
        return packageOrder.getId();
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