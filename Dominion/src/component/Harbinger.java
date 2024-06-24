package component;

import java.util.ArrayList;

public class Harbinger extends Action {

    @Override
    public int cost() { return 5; }

    @Override
    public int cards() { return 2; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {
        
        ArrayList<Card> chosenCard = userInputHandler.chooseCardsFromDiscardPile(
                player,
                "Choose a card to put onto your deck, or decline with 0: ",
                0, 1, "all");

        if (!chosenCard.isEmpty()) {
            player.add_to_hand(chosenCard.get(0));
            player.remove_from_discard(chosenCard.get(0));

            userInputHandler.display("You placed " + chosenCard.toString() + " onto your deck.");
        }

    }

    @Override
    public String toString() { return "Harbinger"; }
    @Override
    public String description() { return super.description() + "Look through your discard pile. You may put a card from it onto your deck."; }

}
