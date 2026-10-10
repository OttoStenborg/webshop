package se.iths.otto.webshop.model;

public class Product {
    private String articleNumber;
    private String title;
    private double price;
    private String description;

    public Product(String articleNumber, String title, double price, String description) {
        this.articleNumber = articleNumber;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    //Metoder

    public String toFileLine() {
        return articleNumber + ";" + title + ";" + price + ";" + description;   // returnerar en String text fråm fil
    }

    public static Product fromFileLine(String line) {
        String[] stringValue = line.split(";", 4);//  Skapar en Product OPJEKT av en String

        String articleNumber = stringValue[0];
        String title = stringValue[1];
        double price = Double.parseDouble(stringValue[2]);
        String description = stringValue[3];

        return new Product(articleNumber, title, price, description);
    }


    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
