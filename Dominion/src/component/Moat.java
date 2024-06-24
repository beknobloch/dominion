package component;

import java.util.ArrayList;

public class Moat extends Action implements Reaction {

    @Override
    public int cost() { return 2; }

    @Override
    public int cards() { return 2; }

    @Override
    public boolean react(GameEvent event, Player player)
    {
        if ( event == GameEvent.ATTACK && userInputHandler.yes_or_no("React with Moat?") )
        {
            player.setVulnerableToAttack(true);
            userInputHandler.display(player.name() + " blocked the attack with a Moat.", false);
            return true;
        }
        return false;
    }

    @Override
    public String toString() { return "Moat"; }
    @Override
    public String description() { return super.description() + "When another player plays an Attack card, you may first reveal this from your hand to be unaffected by it."; }
    @Override
    public ArrayList<Type> type() {
        ArrayList<Type> t = new ArrayList<>();
        t.add(Type.Action);
        t.add(Type.Reaction);
        return t;
    }
}
