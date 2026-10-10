package se.iths.otto.webshop.model;

public class HalfPriceDiscount extends Discount {


    public HalfPriceDiscount(String description) {
        super(description);
    }


    @Override
    public double calculatePriceAfterDiscount(double originalPrice) {
        double discount = 0.5;
        return originalPrice * discount;
    }
}

