package component;

import java.util.ArrayList;

public class Game {

    private UserInputHandler userInputHandler;
    Kingdom kingdom;
    ArrayList<Card> trash = new ArrayList<>();
    ArrayList<Player> players;

    public Game() {
        this(new CLIUserInputHandler());
    }
    public Game(UserInputHandler userInputHandler) {
        this.userInputHandler = userInputHandler;

        trash = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void mainGameFlow()
    {
        this.kingdom = userInputHandler.initiateGame();
        createPlayers(userInputHandler.getPlayerCount());

        while(true)
        {
            for (Player p : players)
            {
                ArrayList<Player> otherPlayers = new ArrayList<>();
                for (Player player : players) {
                    if (player != p) otherPlayers.add(player);
                }

                p.beginTurn();
                p.actionPhase(otherPlayers);
                p.buyPhase(otherPlayers);
                p.cleanup();
            }
        }
    }

    private void createPlayers(int num)
    {
        for (int i = 0 ; i < num ; ++i)
        {
            String name = userInputHandler.getPlayerName();
            players.add(new Player(name, kingdom, trash, userInputHandler));
        }
    }

    public boolean endGame()
    {
        return kingdom.emptyOnProvinces() || kingdom.numEmptySupplyPiles() > 2;
    }

}
