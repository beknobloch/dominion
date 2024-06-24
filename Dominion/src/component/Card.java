package component;

import java.util.ArrayList;

public abstract class Card {

    public abstract int cost();

    public void play(Player player, ArrayList<Player> otherPlayers)
    {
        player.hand().remove(this);
        player.playSpace().add(this);
    }

    @Override
    public abstract String toString();
    public abstract String description();
    public abstract ArrayList<Type> type();

}