package org.sklad.repository;

import org.sklad.db.DB;
import org.sklad.model.Client;
import org.sklad.model.Order;
import org.sklad.model.OrderStatus;
import org.sklad.model.Product;

import java.util.Objects;
import java.util.Optional;

public class ClientRepo {
    private final DB db = DB.getInstance();

    // Adding client to DB
    public void addClientToTable(Client client) {
        db.clients.add(client);
        setCurrentClient(client);
    }
    // Is client already exists in DB
    public boolean isExist(Client client) {
        for (Client dbClient : db.clients) {
            if (dbClient.isEqualTo(client, true)) {
                return true;
            }
        }
        return false;
    }

    // Setting currentClient
    public void setCurrentClient(Client client) {
        db.currentClient = client;
    }

    // Getting current client
    public Client getCurrentClient() {
        return db.currentClient;
    }

    // Checking client
    public boolean checkCurrentClient(Client client){
        for (Client dbClient: db.clients) {
            if (dbClient.isEqualTo(client, false)){
                setCurrentClient(dbClient);
                return true;
            }
        }
        return false;
    }

    public void removeProductFromCart(Product product){
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(product);
        getCurrentClient().cart.remove(product);
    }

    public Order getOrderInfo(){
        return db.orderInfo;
    }
    public void setOrderInfo(Order order){
        db.orderInfo = order;
    }

    public void removeOrder(Order order){
        ProductRepo productRepo = new ProductRepo();
        for (Product product: order.deliveryProducts) {
            productRepo.addProduct(product);
        }
        Client client = db.currentClient;

        Optional<Order> oOrder = client.orders.stream()
                .filter(checkOrder -> checkOrder.getId() == order.getId())
                .findFirst();
        oOrder.ifPresent(value -> {
            value.deliveryStatus = OrderStatus.CANCELED;
            updateClient(client);
        });
    }

    private void updateClient(Client client){
        Optional<Client> oClientDB = db.clients.stream()
                .filter(value -> Objects.equals(value.name, client.name) && Objects.equals(value.address, client.address))
                .findFirst();
        oClientDB.ifPresent(value -> {
            value.updateData(client);
        });
    }

}
