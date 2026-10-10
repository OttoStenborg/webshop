package se.iths.otto.webshop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TenPercentDiscountTest {

    @Test
    @DisplayName("Testar att rabatterade priset blir 450")
    void calculatePriceAfterDiscount() {
        TenPercentDiscount t = new TenPercentDiscount(".");

        double discountedPrice = t.calculatePriceAfterDiscount(500);
        Assertions.assertEquals(450,discountedPrice);
    }
}