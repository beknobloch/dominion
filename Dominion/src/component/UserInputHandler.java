package component;

import java.util.ArrayList;

public interface UserInputHandler {

    Kingdom initiateGame();
    int getPlayerCount();
    String getPlayerName();

    void display(String message);
    void display(String message, boolean requireButtonPress);

    boolean yes_or_no(String question);

    void markNewPlayerTurn(Player player);
    void displayRemainingBuys(Player player);
    void displayRemainingActions(Player player);
    void concludePlayerTurn(Player player);

    Card chooseCardFromHand(Player player, String message);
    ArrayList<Card> chooseCardsFromHand(Player player, String message, int minCardsToChoose, int maxCardsToChoose, String type);
    
    ArrayList<Card> chooseCardsFromDiscardPile(Player player, String message, int minCardsToChoose, int maxCardsToChoose, String type);

    ArrayList<Card> selectCardsFromSupply(Player player, String message, int minCardsToChoose, int maxCardsToChoose);

}
