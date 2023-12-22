package org.sklad.repository;

import org.sklad.db.DB;
import org.sklad.model.*;
import org.sklad.model.Package;
import org.sklad.util.Utils;

import java.util.ArrayList;
import java.util.Objects;

public class ManagerRepo {
    private final DB db = DB.getInstance();

    // ---------------------------- Orders
    private ArrayList<ClientOrder> getOrders() {
        ArrayList<ClientOrder> clientOrders = new ArrayList<>();

        for (Client client : db.clients)
            clientOrders.addAll(client.clientOrders);
        return clientOrders;
    }

    public ArrayList<ClientOrder> getOrdersBy(boolean formed) {
        ArrayList<ClientOrder> orders = new ArrayList<>();
        for (Client client : db.clients) {
            for (ClientOrder clientOrder : client.clientOrders) {
                if (formed) {
                    if (clientOrder.deliveryStatus == OrderStatus.READY_TO_DELIVER) {
                        orders.add(clientOrder);
                    }
                } else {
                    if (clientOrder.deliveryStatus == OrderStatus.IN_PROCESS) {
                        orders.add(clientOrder);
                    }
                }
            }
        }
        return orders;
    }

    public void updateOrderStatus(Package pkg, OrderStatus status) {
        for (ClientOrder clientOrder : getOrders()) {
            if (clientOrder.getId() == pkg.getOderId()) {
                clientOrder.deliveryStatus = status;
            }
        }
    }

    // ---------------------------- Packages
    public ArrayList<Package> getAvailablePackages() {
        ArrayList<Package> packages = new ArrayList<>();
        for (ClientOrder clientOrder : getOrders()) {
            if (Utils.isItToday(clientOrder.deliveryDate) &&
                    clientOrder.deliveryStatus == OrderStatus.READY_TO_DELIVER
            )
                packages.add(new Package(clientOrder));
        }
        return packages;
    }

    public void setCurrentPackage(Package pkg) {
        db.deliveringPackage = pkg;
    }

    public Package getCurrentPackage() {
        return db.deliveringPackage;
    }

    // ---------------------------- Providers
    public ArrayList<Provider> getProviders() {
        return db.providers;
    }

    public void addProvider(Provider provider) {
        db.providers.add(provider);
    }

    public void removeProvider(Provider provider) {
        db.providers.remove(provider);
    }

    public void setCurrentProvider(Provider provider) {
        db.currentProvider = provider;
    }

    public Provider getCurrentProvider() {
        return db.currentProvider;
    }

    public String findDescriptionById(int id){
        for (Product product: getCurrentProvider().getProducts())
            if (product.id == id)
                return product.description;
        return "";
    }

    // ---------------------------- Storage Orders
    public ArrayList<StorageOrder> getAllStorageOrders() {
        return db.storageOrders;
    }

    public ArrayList<StorageOrder> getReadyOrders() {
        ArrayList<StorageOrder> readyOrders = new ArrayList<>();
        for (StorageOrder order: getAllStorageOrders()) {
            if (order.getStatus() == OrderStatus.WAITING_TO_ACCEPT){
                readyOrders.add(order);
            }
        }
        return readyOrders;
    }

    public void updateOrderStatuses(){
        for (StorageOrder order: getAllStorageOrders()) {
            if (order.getStatus() == OrderStatus.BEING_DELIVERED){
                if (Objects.equals(order.getDeliveryDate(), Utils.getCurrentDate())){
                    order.setStatus(OrderStatus.WAITING_TO_ACCEPT);
                }
            }
        }
    }

    // TODO доработать наверное
    public void addToStorageOrders(StorageOrder storageOrder) {
        db.storageOrders.add(storageOrder);
    }

    public void removeFromStorageOrder(StorageOrder storageOrder) {
        db.storageOrders.remove(storageOrder);
    }

}
