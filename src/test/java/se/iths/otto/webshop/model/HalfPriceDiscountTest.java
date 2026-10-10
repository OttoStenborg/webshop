package se.iths.otto.webshop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HalfPriceDiscountTest {

    @Test
    @DisplayName("Testar att rabatterade priset blir 250")
    void calculatePriceAfterDiscount() {
        HalfPriceDiscount t = new HalfPriceDiscount(".");

        double discountedPrice = t.calculatePriceAfterDiscount(500);
        Assertions.assertEquals(250,discountedPrice);
    }
}