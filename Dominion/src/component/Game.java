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

                if (endOfGame())
                    break;
            }

            if (endOfGame())
                break;
        }

        userInputHandler.display("The game is over! Proceed to see the winner...");

        Player winner = determineWinner();
        userInputHandler.display("The winner is " + winner.name() + "!!!");
    }

    private void createPlayers(int num)
    {
        for (int i = 0 ; i < num ; ++i)
        {
            String name = userInputHandler.getPlayerName();
            players.add(new Player(name, kingdom, trash, userInputHandler));
        }
    }

    public boolean endOfGame()
    {
        return kingdom.emptyOnProvinces() || kingdom.numEmptySupplyPiles() > 2;
    }

    public Player determineWinner()
    {
        int maxScore = Integer.MIN_VALUE;
        Player winningPlayer = new Player();
        for (Player p : players)
        {
            int thisScore = 0;
            for (Card c : p.hand())
            {
                if (c.type().contains(Type.VICTORY) || c.type().contains(Type.CURSE))
                    thisScore += c.victory_points();
            }
            if (thisScore > maxScore)
            {
                maxScore = thisScore;
                winningPlayer = p;
            }
        }
        return winningPlayer;
    }

}
