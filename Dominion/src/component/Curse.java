package component;

import java.util.ArrayList;

public class Curse extends Card {

    public int victory_points() { return -1; }

    @Override
    public int cost() { return 0; }

    @Override
    public void play(Player player, ArrayList<Player> otherPlayers) { }

    @Override
    public String toString() { return "Curse"; }

    @Override
    public String description() {
        return this.victory_points() + " VP";
    }

    @Override
    public ArrayList<Type> type()
    {
        ArrayList<Type> t = new ArrayList<>();
        t.add(Type.Curse);
        return t;
    }

}
