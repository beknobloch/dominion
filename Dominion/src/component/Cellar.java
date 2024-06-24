package component;

import java.util.ArrayList;

public class Cellar extends Action {

    public Cellar(UserInputHandler uih) {
        super(uih);
    }

    @Override
    public int cost() { return 2; }

    @Override
    public int actions() { return 1; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {
        
        ArrayList<Card> cardsToDiscard = userInputHandler.chooseCardsFromHand(
                player,
                "Choose any number of cards to discard (enter 0 to stop): ",
                0, -1, "all");

        for (Card card : cardsToDiscard) {
            player.discard(card);
            player.remove_from_hand(card);
        }

        player.draw(cardsToDiscard.size());
        
    }

    @Override
    public String toString() { return "Cellar"; }
    @Override
    public String description() { return "Discard any number of cards, then draw that many."; }

}
