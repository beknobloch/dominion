package component;

import java.util.ArrayList;

public class Remodel extends Action {

    @Override
    public int cost() { return 4; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {

        Card chosenCard = userInputHandler.chooseCardsFromHand(player, "Choose a card to trash.", 1, 1, "all").get(0);
        
        player.trash(chosenCard);
        player.remove_from_hand(chosenCard);
        
        Card upgrade;
        do { 
            upgrade = userInputHandler.selectCardsFromSupply(player, String.format("Gain a card costing up to %d.", chosenCard.cost()), 1, 1).get(0);
        } while (upgrade.cost() > chosenCard.cost() + 2);

        player.gain(upgrade);

        userInputHandler.display(player.name() + " gained a " + upgrade + ".", false);

    }

    @Override
    public String toString() { return "Remodel"; }
    @Override
    public String description() { return "Trash a card from your hand. Gain a card costing up to $2 more than it."; }

}