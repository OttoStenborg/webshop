package se.iths.otto.webshop;

public class IOService implements OutInputHandler {


    @Override
    public String prompt(String message) {
        return IO.readln(message);
    }

    @Override
    public void info(String message) {
        IO.println(message);
    }

    @Override
    public String menu() {
        IO.println("1. Lägg till en produkt");
        IO.println("2. Lista alla produkter");
        IO.println("3. Visa all information om en produkt");
        IO.println("4. Avsluta programmet");
        return IO.readln("Ange ett alternativ");
    }
}
