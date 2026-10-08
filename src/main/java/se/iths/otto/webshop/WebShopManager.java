package se.iths.otto.webshop;

import se.iths.otto.webshop.model.Discount;
import se.iths.otto.webshop.model.HalfPriceDiscount;
import se.iths.otto.webshop.model.Product;
import se.iths.otto.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private ProductStorage productStorage;
    private boolean isRunning = true;

    public WebShopManager(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    private void saveProductToStorage(Product product){
        productStorage.saveProduct(product);
    }

    private List<Product> getProductsFromStorage(){
        return productStorage.getProducts();
    }

    private Product getProductFromStorage(String articleNumber){
        return productStorage.getProduct(articleNumber);
    }


    public void startWebShop(){
        Discount discount = null;
        String discountCode = IO.readln("Ange rabattkod");
        if (discountCode.equalsIgnoreCase("halfprice")){
            discount = new HalfPriceDiscount("50% Discount");
        }
        else {
            discount = new TenPercentDiscount("10% Discount");
        }
        while(isRunning){
            IO.println("1. Lägg till en produkt");
            IO.println("2. Lista alla produkter");
            IO.println("3. Visa all information om en produkt");
            IO.println("4. Avsluta programmet");
            String input = IO.readln("Ange ett alternativ");
            switch (input){
                case "1":
                    String articleNumber = IO.readln("Ange artikelnr:  ");
                    String title = IO.readln("Ange namn:  ");
                    double price = Double.parseDouble(IO.readln("Ange pris:  "));
                    String description = IO.readln("Ange en beskrivning:  ");
                    Product product = new Product(articleNumber,title,price,description);
                    saveProductToStorage(product);
                    break;
                case "2":
                    List<Product> products = getProductsFromStorage();
                    for(Product row : products) {
                        double discountedPrice = discount.calculatePriceAfterDiscount(row.getPrice());
                        IO.println("Artikelnummer:  " + row.getArticleNumber());
                        IO.println("Namn:  " + row.getTitle());
                        IO.println("Nytt pris:  " + discountedPrice);//anropas lokalt från samma klass
                        IO.println("Beskrivning:  " + row.getDescription());
                        IO.println("---------------------------------------------------------------------");
                    }
                    break;
                case "3":
                    articleNumber = IO.readln("Ange artikelnr:  ");
                    Product isFound = getProductFromStorage(articleNumber);
                    if (isFound == null){
                        IO.println("Produkten finns inte");
                    }else{
                        IO.println(isFound.toFileLine());
                    }
                    break;
                case "4":
                    isRunning = false;


            }
        }

    }
}
