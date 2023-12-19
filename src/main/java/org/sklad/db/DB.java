package org.sklad.db;

import org.sklad.model.Client;
import org.sklad.model.Order;
import org.sklad.model.Package;
import org.sklad.model.Product;
import org.sklad.util.Constant;

import java.util.ArrayList;
import java.util.List;

// Singleton
public class DB {
    private static DB instance;

    private DB() {
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    //---------------------------------------------------------------------------//
    // Tables
    public ArrayList<Client> clients = new ArrayList<>();
    public ArrayList<Product> productsInWarehouse = new ArrayList<>(
            List.of(
                    new Product(0, "Prod 0", "description 0", 100, 34,    Constant.img0),
                    new Product(1, "Prod 1", "description 1", 65,  63,    Constant.img1),
                    new Product(2, "Prod 2", "description 2", 25,  123,   Constant.img2),
                    new Product(3, "Prod 3", "description 3", 11,  154,   Constant.img3),
                    new Product(4, "Prod 4", "description 4", 643, 4,     Constant.img4),
                    new Product(5, "Prod 5", "description 5", 42,  2262,  Constant.img5),
                    new Product(6, "Prod 6", "description 6", 86,  25234, Constant.img6),
                    new Product(7, "Prod 7", "description 7", 97,  867,   Constant.img7),
                    new Product(8, "Prod 8", "description 8", 1,   949,   Constant.img8),
                    new Product(9, "Prod 9", "description 9", 53,  1000,  Constant.img9)
            )
    );


    // User
    public Client currentClient;
    public Order orderInfo;
    public Package deliveringPackage;

}
