package se.iths.otto.webshop;

public class Main {
    static void main() {
        ProductStorage productStorage = new ProductFileStorage();
        OutInputHandler ioService = new IOService();
        WebShopManager manager = new WebShopManager(productStorage, ioService);
        manager.startWebShop();

    }
}
