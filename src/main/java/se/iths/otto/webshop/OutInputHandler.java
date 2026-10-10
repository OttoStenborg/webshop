package se.iths.otto.webshop;

public interface OutInputHandler {
    String prompt(String message);
    //Metoden ska visa ett meddelande till användaren och returnera användarens svar

    void info(String message);
    //Metoden ska visa information för användaren

    String menu();
    //Metoden ska visa applikationens meny och returnera användarens val
}

