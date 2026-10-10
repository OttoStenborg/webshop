package se.iths.otto.webshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.otto.webshop.model.Product;

public class WebShopManagerTest {
    ProductListStorage productListStorage;
    WebShopManager manager;

    @BeforeEach
    void setUp() {
        productListStorage = new ProductListStorage();
        manager = new WebShopManager(productListStorage, null);
    }


    @Test
    @DisplayName("Testar att listan innehåller 2 objekt")
    void saveAndCheckList() {
        Product productOne = new Product("12", "dator", 50, "en dator");
        Product productTwo = new Product("50", "musmatta", 500, "en musmatta");
        manager.saveProductToStorage(productOne);
        manager.saveProductToStorage(productTwo);

        Assertions.assertEquals(2, manager.getProductsFromStorage().size());
    }

    @Test
    @DisplayName("Testar att metod kan hitta ett objekt med artikelnummer")
    void findProduct() {
        Product productOne = new Product("12", "dator", 50, "en dator");
        manager.saveProductToStorage(productOne);

        Product test = manager.getProductFromStorage("12");
        Assertions.assertEquals("12", test.getArticleNumber());
    }

    @Test
    @DisplayName("Testar om metod returnerar null om metoden inte hittar rätt objekt")
    void testListContainsNull() {
        manager.getProductFromStorage("12");
        Assertions.assertNull(manager.getProductFromStorage("12"));
    }
}
