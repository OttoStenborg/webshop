package se.iths.otto.webshop;

import se.iths.otto.webshop.model.Product;

import java.util.List;

public interface ProductStorage {
    void saveProduct(Product product);
    //Sparar product och skriver ut per rad så här articleNumber;title;price;description

    List<Product> getProducts();
    // Returnerar en lista på produkter

    Product getProduct(String articleNumber);
    // returnerar en produkt utifrån articleNumber
}
