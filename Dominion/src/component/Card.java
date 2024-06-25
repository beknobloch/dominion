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
    public String typesToString()
    {
        String v = "(";
        if (!type().isEmpty())
            v += type().get(0);
        for (int i = 1 ; i < type().size() ; i++)
            v += ", " + type().get(i);
        v += ")";
        return v;
    }
    public abstract String description();
    public abstract ArrayList<Type> type();

}
