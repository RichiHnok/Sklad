package org.sklad.repository;

import org.sklad.db.DB;
import org.sklad.model.Client;
import org.sklad.model.Order;
import org.sklad.model.OrderStatus;
import org.sklad.model.Package;
import org.sklad.util.Utils;

import java.util.ArrayList;

public class ManagerRepo {
    private DB db = DB.getInstance();

    public ArrayList<Order> getOrders(){
        ArrayList<Order> orders = new ArrayList<>();

        for (Client client: db.clients) {
            for (Order order: client.orders) {
                    orders.add(order);
            }
        }
        return orders;
    }


    public ArrayList<Package> getAvailablePackages(){
        ArrayList<Package> packages = new ArrayList<>();
        for (Order order: getOrders()) {
            if (Utils.isItToday(order.deliveryDate) &&
                order.deliveryStatus != OrderStatus.CANCELED &&
                order.deliveryStatus != OrderStatus.BEING_DELIVERED &&
                order.deliveryStatus != OrderStatus.DELIVERED
            )
                packages.add(new Package(order));
        }
        return packages;
    }

    public void updateOrderStatus(Package pkg, OrderStatus status){
        for (Order order: getOrders()) {
            if (order.getId() == pkg.getOderId()){
                order.deliveryStatus = status;
            }
        }
    }

    public void setCurrentPackage(Package pkg){
        db.deliveringPackage = pkg;
    }

    public Package getCurrentPackage(){
        return db.deliveringPackage;
    }

}
