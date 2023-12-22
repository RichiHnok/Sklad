package org.sklad.db;

import java.io.*;

// Singleton
public abstract class Serializer {

     public static boolean doesFileExist(String path) {
        File dbFile = new File(path);
        return dbFile.exists();
    }

    public static void saveToFile(DB db, String path){
        try (FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(db);
        } catch (IOException e) {
            System.err.println("An error occurred while saving DB to the file " + path + ": " + e.getMessage());
        }
    }

    public static DB loadFromFile(String path){
        DB dbInstance = null;
        try (FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            dbInstance = (DB) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while loading DB form the file " + path + ": " + e.getMessage());
        }
        return dbInstance;
    }
}