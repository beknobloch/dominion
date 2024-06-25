package component;

import java.util.ArrayList;

public class Workshop extends Action {

    @Override
    public int cost() { return 4; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {

        Card choice;
        while (true) { 
            try {
                choice = userInputHandler.selectCardsFromSupply(player, "Choose a card to gain (up to $4).", 1, 1).get(0);
                player.gain(choice);
                break;
            } catch (Exception e) {
            }
        }
        
        userInputHandler.display(player.name() + " gained a " + choice + ".", false);

    }

    @Override
    public String toString() { return "Workshop"; }
    @Override
    public String description() { return "Gain a card costing up to $4."; }

}