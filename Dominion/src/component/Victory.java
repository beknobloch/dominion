package component;

import java.util.ArrayList;

public abstract class Victory extends Card {
    
    public abstract int victory_points();

    @Override
    public void play(Player player, ArrayList<Player> otherPlayers) { }

    @Override
    public String description()
    {
        return this.victory_points() + " VP";
    }

    @Override
    public ArrayList<Type> type()
    {
        ArrayList<Type> t = new ArrayList<>();
        t.add(Type.VICTORY);
        return t;
    }

}
