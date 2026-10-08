package se.iths.otto.webshop.model;

public class TenPercentDiscount extends Discount{
    public TenPercentDiscount(String description) {
        super(description);
    }

    @Override
    public double calculatePriceAfterDiscount(double originalPrice) {
        double discount = 0.9;
        return originalPrice * discount;
    }
}
