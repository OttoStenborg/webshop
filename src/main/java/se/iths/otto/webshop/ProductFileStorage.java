package se.iths.otto.webshop;

import se.iths.otto.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {
    private final Path file = Path.of("products.txt");  // Beskriver var filen sparas


    @Override
    public void saveProduct(Product product) {
        try {
            Files.writeString(file, product.toFileLine() + System.lineSeparator() // Skriver en String rad av en produkt objekt
                    , StandardOpenOption.CREATE, StandardOpenOption.APPEND);  // lägger till String raden och skapar filen OM den inte har skapats innan
        } catch (IOException e) {
            System.err.println("fel vid hantering" + e.getMessage());
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productsList = new ArrayList<>();

        if (Files.notExists(file)) {
            return productsList; // Returnerar en tom lista om filen inte finns
        } else {
            try {
                List<String> productRow = Files.readAllLines(file);
                for (String row : productRow) {
                    Product product = Product.fromFileLine(row); // Hela denna kod gör att den läser alla produkter som String objekt
                    productsList.add(product); //                   och sedan konverterar till Product objekt för att därefter
                }//                                                 lägga in i listan av product Objekt
            } catch (IOException e) {
                System.err.println("fel vid hantering" + e.getMessage());
            }
            return productsList; // returnerar hela listan med Product OBJEKT
        }
    }

    @Override
    public Product getProduct(String articleNumber) {
        List<Product> products = getProducts();  //Använder metoden ovan för att skapa Lista på alla produkter
        for (Product product : products) { // Letar efter produkt utifrån artikelnummer
            if (product.getArticleNumber().equals(articleNumber)) {
                return product; // returnerar produkt
            }
        }
        return null; // returnerar null om inget finns
    }

}
