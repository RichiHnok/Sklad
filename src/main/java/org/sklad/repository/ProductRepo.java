package org.sklad.repository;

import org.sklad.db.DB;
import org.sklad.model.Product;

import java.util.ArrayList;
import java.util.Optional;

public class ProductRepo {
    private final DB db = DB.getInstance();

    public ArrayList<Product> getProductList() {
        return db.products;
    }

    public ArrayList<Product> getVisibleProductList() {
        ArrayList<Product> visibleProducts = new ArrayList<>();
        for (Product product: db.products) {
             if (product.visible){
                 visibleProducts.add(product);
             }
        }
        return visibleProducts;
    }

    public int takeSomeAmountOfProduct(Product product, int amount) {
        Optional<Product> oProduct = db.products.stream()
                .filter(element -> element.id == product.id)
                .findFirst();

        oProduct.ifPresent(value -> {
            value.availableAmount -= amount;
            if (value.availableAmount == 0){
//
                db.products.remove(value);
            }
        });
        return amount;
    }

    public void addProduct(Product product){
        Optional<Product> oProduct = db.products.stream()
                .filter(element -> element.id == product.id)
                .findFirst();

        if (oProduct.isEmpty()){
            System.out.println("There is not such product");
            db.products.add(product);
        } else {
            System.out.println("There is such product");
            oProduct.ifPresent(value -> {
                value.availableAmount += product.availableAmount;
            });
        }
    }
}
