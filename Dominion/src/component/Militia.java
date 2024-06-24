package component;

import java.util.ArrayList;

public class Militia extends Action {

    @Override
    public int cost() { return 4; }

    @Override
    public int coin() { return 2; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {
        for (Player p : otherPlayers)
        {
            if (p.attack())
                p.discardDownTo(3);
        }
    }

    @Override
    public String toString() { return "Militia"; }
    @Override
    public String description() { return super.description() + "Each other player discards down to three cards in hand."; }

}
