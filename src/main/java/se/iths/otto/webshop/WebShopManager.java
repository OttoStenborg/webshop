package se.iths.otto.webshop;

import se.iths.otto.webshop.model.Discount;
import se.iths.otto.webshop.model.HalfPriceDiscount;
import se.iths.otto.webshop.model.Product;
import se.iths.otto.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private final ProductStorage productStorage;
    private final OutInputHandler ioService;

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

    //Metoder som är private och styr menyn

    private Discount checkDiscount() {
        Discount discount = null;
        String discountCode = ioService.prompt("Ange rabattkod");
        if (discountCode.equalsIgnoreCase("halfprice")) {
            discount = new HalfPriceDiscount("50% Discount");
            return discount;
        } else {
            discount = new TenPercentDiscount("10% Discount");
            return discount;
        }
    }

    private void addProduct() {
        double price;
        String articleNumber = ioService.prompt("Ange artikelnr:  ");
        String title = ioService.prompt("Ange namn:  ");
        while (true) {
            try {
                price = Double.parseDouble(ioService.prompt("Ange pris:  "));
                break;
            } catch (NumberFormatException e) {
                ioService.prompt("Du behöver ange siffror, inga tecken eller ',' tecken!");
            }
        }
        String description = ioService.prompt("Ange en beskrivning:  ");
        Product product = new Product(articleNumber, title, price, description);
        saveProductToStorage(product);
    }

    private void printProducts(Discount discount) {
        List<Product> products = getProductsFromStorage();
        for (Product product : products) {
            double discountedPrice = discount.calculatePriceAfterDiscount(product.getPrice());
            printProductInformation(product, discountedPrice);
        }
    }

    private void printProductInformation(Product product, double discountedPrice) {
        ioService.info("---------------------------------------------------------------------");
        ioService.info("Artikelnummer:  " + product.getArticleNumber());
        ioService.info("Namn:  " + product.getTitle());
        ioService.info("Nytt pris:  " + discountedPrice);//anropas lokalt från samma klass
        ioService.info("Gammalt pris:  " + product.getPrice());
        ioService.info("Beskrivning:  " + product.getDescription());
        ioService.info("---------------------------------------------------------------------");
    }

    private void findProduct(Discount discount) {
        String articleNumber = ioService.prompt("Ange artikelnr:  ");
        Product product = getProductFromStorage(articleNumber);
        if (product == null) {
            ioService.info("Produkten finns inte");
        } else {
            double discountedPrice = discount.calculatePriceAfterDiscount(product.getPrice());
            printProductInformation(product, discountedPrice);
        }
    }

    //Start metod som är public

    public void startWebShop() {
        boolean isRunning = true;
        Discount discount = checkDiscount();
        while (isRunning) {
            String input = ioService.menu();
            switch (input) {
                case "1" -> addProduct();
                case "2" -> printProducts(discount);
                case "3" -> findProduct(discount);
                case "4" -> isRunning = false;
                default -> ioService.info("Ange ett giltigt alternativ");
            }
        }

    }
}
