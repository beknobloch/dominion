package component;

import java.util.ArrayList;

public class Witch extends Action {

    @Override
    public int cost() { return 5; }

    @Override
    public int cards() { return 2; }

    @Override
    public void effect(Player player, ArrayList<Player> otherPlayers)
    {
        for (Player p : otherPlayers)
        {
            if (p.attack())
                p.gain("Curse");
        }
    }

    @Override
    public String toString() { return "Witch"; }
    @Override
    public String description() { return super.description() + "Each other player gains a curse."; }

}
