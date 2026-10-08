package se.iths.otto.webshop;

public class Main {
    static void main() {
        ProductStorage productStorage = new ProductFileStorage();
        WebShopManager manager = new WebShopManager(productStorage);
        manager.startWebShop();

    }
}
