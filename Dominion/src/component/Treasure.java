package component;

import java.util.ArrayList;

public abstract class Treasure extends Card {

    public abstract int value();

    @Override
    public void play(Player player, ArrayList<Player> otherPlayers)
    {
        super.play(player, otherPlayers);
        player.earn_coins(value());
    }

    @Override
    public String description()
    {
        return "$" + this.value();
    }

    @Override
    public ArrayList<Type> type()
    {
        ArrayList<Type> t = new ArrayList<>();
        t.add(Type.Treasure);
        return t;
    }

}
