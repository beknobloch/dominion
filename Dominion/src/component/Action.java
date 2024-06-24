package component;

import java.util.ArrayList;

public abstract class Action extends Card {

    UserInputHandler userInputHandler;

    public int cards() { return 0; };
    public int actions() { return 0; };
    public int coin() { return 0; };
    public int buys() { return 0; };

    public Action() {
        this(new CLIUserInputHandler());
    }
    public Action(UserInputHandler uih) {
        userInputHandler = uih;
    }

    public void effect(Player currentPlayer, ArrayList<Player> otherPlayers) {};

    @Override
    public void play(Player player, ArrayList<Player> otherPlayers)
    {
        super.play(player, otherPlayers);
        
        player.draw(cards());
        player.get_actions( actions() );
        player.get_buys( buys() );
        player.earn_coins( coin() );

        effect(player, otherPlayers);

    }

    @Override
    public String description() {
        String d = "";
        
        if (cards() > 0) d += "+" + cards() + " cards    ";
        if (actions() > 0) d += "+" + actions() + " actions    ";
        if (coin() > 0) d += "+" + buys() + " buys    ";
        if (buys() > 0) d += "+$" + coin() + "   ";

        return d;
    }

    @Override
    public ArrayList<Type> type() {
        ArrayList<Type> t = new ArrayList<>();
        t.add(Type.Action);
        return t;
    }
    
}
