package org.sklad;

public class Package {
    
    private String packageOrder = null;
    private String clientAddress = null;
    private String deliveryDate = null;

    // Placeholder
    public Package(){
        packageOrder = new String("Z000");
        clientAddress = new String("default address");
        deliveryDate = new String("29.12.2000");
    }

    public String getPackageOrder(){
        return packageOrder;
    }

    public String getClientAddress(){
        return clientAddress;
    }

    public String getDeliveryDate(){
        return deliveryDate;
    }
}
