package se.iths.otto.webshop;

import se.iths.otto.webshop.model.Discount;
import se.iths.otto.webshop.model.HalfPriceDiscount;
import se.iths.otto.webshop.model.Product;
import se.iths.otto.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private ProductStorage productStorage;
    private OutInputHandler ioService;
    private boolean isRunning = true;

    public WebShopManager(ProductStorage productStorage, OutInputHandler ioService) {
        this.productStorage = productStorage;
        this.ioService = ioService;
    }


    //Metoder som också är package private

    void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }


    //Start metod som är public

    public void startWebShop() {
        Discount discount = null;
        String discountCode = ioService.prompt("Ange rabattkod");
        if (discountCode.equalsIgnoreCase("halfprice")) {
            discount = new HalfPriceDiscount("50% Discount");
        } else {
            discount = new TenPercentDiscount("10% Discount");
        }
        while (isRunning) {
            String input = ioService.menu();
            switch (input) {
                case "1":
                    String articleNumber = ioService.prompt("Ange artikelnr:  ");
                    String title = ioService.prompt("Ange namn:  ");
                    double price = Double.parseDouble(ioService.prompt("Ange pris:  "));
                    String description = ioService.prompt("Ange en beskrivning:  ");
                    Product product = new Product(articleNumber, title, price, description);
                    saveProductToStorage(product);
                    break;
                case "2":
                    List<Product> products = getProductsFromStorage();
                    for (Product row : products) {
                        double discountedPrice = discount.calculatePriceAfterDiscount(row.getPrice());
                        ioService.info("Artikelnummer:  " + row.getArticleNumber());
                        ioService.info("Namn:  " + row.getTitle());
                        ioService.info("Nytt pris:  " + discountedPrice);//anropas lokalt från samma klass
                        ioService.info("Gammalt pris:  " + row.getPrice());
                        ioService.info("Beskrivning:  " + row.getDescription());
                        ioService.info("---------------------------------------------------------------------");
                    }
                    break;
                case "3":
                    articleNumber = ioService.prompt("Ange artikelnr:  ");
                    Product specifikProduct = getProductFromStorage(articleNumber);
                    if (specifikProduct == null) {
                        ioService.info("Produkten finns inte");
                    } else {
                        double discountedPrice = discount.calculatePriceAfterDiscount(specifikProduct.getPrice());
                        ioService.info(specifikProduct.toFileLine());
                        ioService.info("Artikelnummer:  " + specifikProduct.getArticleNumber());
                        ioService.info("Namn:  " + specifikProduct.getTitle());
                        ioService.info("Nytt pris:  " + discountedPrice);
                        ioService.info("Gammalt pris:  " + specifikProduct.getPrice());
                        ioService.info("Beskrivning:  " + specifikProduct.getDescription());
                    }
                    break;
                case "4":
                    isRunning = false;
            }
        }

    }
}
