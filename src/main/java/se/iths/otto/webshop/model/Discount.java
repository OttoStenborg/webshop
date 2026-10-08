package se.iths.otto.webshop.model;

public abstract class Discount {
    protected String description;

    public Discount(String description) {
        this.description = description;
    }

    public abstract double calculatePriceAfterDiscount(double originalPrice);
    // Skall returnera pris EFTER avdrag




    public String getDescription() {
        return description;
    }
}
