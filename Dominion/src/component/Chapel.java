package component;

import java.util.ArrayList;

public class Chapel extends Action {

    @Override
    public int cost() { return 2; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {
        
        ArrayList<Card> cardsToDiscard = userInputHandler.chooseCardsFromHand(
                player,
                "Choose up to four cards to trash (enter 0 to stop): ",
                0, 4, "all");

        for (Card card : cardsToDiscard) {
            player.trash(card);
            player.remove_from_hand(card);
        }
        
    }

    @Override
    public String toString() { return "Chapel"; }
    @Override
    public String description() { return "Trash up to 4 cards from your hand."; }

}
