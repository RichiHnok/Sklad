package org.sklad.db;

import org.sklad.model.*;
import org.sklad.model.Package;

import java.io.*;
import java.util.ArrayList;

// Singleton
public class DB implements Serializable {
    private static DB instance;
    public ArrayList<Client> clients = new ArrayList<>();
    public ArrayList<Product> products = new ArrayList<>();
    public ArrayList<Provider> providers = new ArrayList<>();
    public ArrayList<StorageOrder> storageOrders = new ArrayList<>();
    public Client currentClient;
    public ClientOrder clientOrderInfo;
    public Package deliveringPackage;
    public Provider currentProvider;

    private DB() {
    }

    public static DB getInstance() {
        if (instance == null) {
            if (doesFileExists()) {
                getFromFile();
                System.out.println("Получаем из файла");
            } else {
                instance = new DB();
                System.out.println("в файле нихера нет");
            }
        }
        System.out.println("Теперь вроде все ок");
        return instance;

    }

    //-----------------Serialization-----------------//
    private static final String FILE_NAME = "db.ser";

    public static void saveToFile() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(instance);
            System.out.println("Object has been serialized and saved to object.ser");
        } catch (IOException e) {
            System.out.println("что то не сериализуется");
            e.printStackTrace();
        }
    }

    private static void getFromFile() {
        // Deserialization
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            instance = (DB) objectIn.readObject();
            System.out.println("Object has been deserialized");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean doesFileExists() {
        File file = new File(FILE_NAME);
        return file.exists();
    }
}
