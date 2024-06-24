package component;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    String name;
    UserInputHandler uih;

    ArrayList<Card> hand;
    ArrayList<Card> playSpace;
    ArrayList<Card> drawPile;
    ArrayList<Card> discardPile;

    Kingdom kingdom;
    ArrayList<Card> trash;

    int numActions;
    int numBuys;
    int numCoins;

    int victoryPointCount;

    public Player(String name, Kingdom kingdom, ArrayList<Card> trash, UserInputHandler uih) {

        this.name = name;
        this.uih = uih;
        this.kingdom = kingdom;
        this.trash = trash;

        numActions = 1;
        numBuys = 1;
        numCoins = 0;

        initializePlayer();

    }

    private void initializePlayer()
    {
        ArrayList<Card> startingDeck = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++) { startingDeck.add(new Copper()); }
        for (int i = 0 ; i < 3 ; i++) { startingDeck.add(new Estate()); }
        discardPile = startingDeck;

        hand = new ArrayList<>();
        drawPile = new ArrayList<>();
        playSpace = new ArrayList<>();

        draw(5);
    }

    public void beginTurn()
    {
        uih.markNewPlayerTurn(this);
    }

    public void actionPhase(ArrayList<Player> otherPlayers)
    {
        
        uih.display("---\nACTION PHASE\n---\n\n\nYour hand contains " + hand() + ".");

        while (numActions > 0 && hasActions()) {
            
            uih.displayRemainingActions(this);
            ArrayList<Card> actionCard = uih.chooseCardsFromHand(this, "Choose a card to play, or enter 0 to begin the Buy Phase.", 0, 1, "action");
            if (actionCard.isEmpty())
            {
                uih.display("Skipping to buy phase.");
                break;
            }
            else
            {
                --numActions;
                actionCard.get(0).play(this, otherPlayers);
            }

        }
        if (numActions < 1) uih.display("Actions exhausted; Proceeding to the Buy Phase.");
        else if (!hasActions()) uih.display("Your hand is empty of actions; Proceeding to the Buy Phase.");

    }

    public void buyPhase(ArrayList<Player> otherPlayers)
    {
        System.out.println(hand);
        uih.display("---\nBUY PHASE\n---");

        ArrayList<Card> cardsToPlay = new ArrayList<>();
        for (Card c : hand) {
            if (c instanceof Treasure) {
                cardsToPlay.add(c);
            }
        }

        for (Card c : cardsToPlay) {
            c.play(this, otherPlayers);
            hand.remove(c);
        }

        while (numBuys > 0)
        {
            uih.displayRemainingBuys(this);
            ArrayList<Card> boughtCard = uih.selectCardsFromSupply(this, "Choose a card to buy, or 0 to clean up and end your turn.", 0, 1);
            
            if (boughtCard.isEmpty())
                break;

            for (Supply<Card> s : kingdom.kingdom())
            {
                if (s.displayCard().getClass() == boughtCard.get(0).getClass())
                {
                    try {
                        gain(s.pull_card());
                        numBuys--;
                        uih.display("You purchased a " + boughtCard.get(0) + ".");
                    } catch (Exception e) {
                        uih.display("The supply pile for " + s.displayCard().toString() + "s is empty.");
                    }
                    break;
                }
            }
        }
        if (numBuys < 1)
            uih.display("You're out of buys.");
    }

    public void cleanup()
    {
        numActions = 1;
        numBuys = 1;
        numCoins = 0;

        for (Card c : hand) { discard(c); }
        for (Card c : playSpace) { discard(c); }
        hand = new ArrayList<>();
        playSpace = new ArrayList<>();
        draw(5);
    }

    public String handleEvent(GameEvent event)
    {
        String reactionOutput = "none";
        for (Card c : hand)
        {
            if (c instanceof Reaction)
            {
                reactionOutput = c.react(event);
            }
        }
        return reactionOutput;
    }

    public String name() {
        return name;
    }

    public ArrayList<Card> hand() {
        return hand;
    }

    public ArrayList<Card> playSpace() {
        return playSpace;
    }

    public ArrayList<Card> discardPile() {
        return discardPile;
    }

    public Kingdom kingdom() {
        return kingdom;
    }

    public int actions() {
        return numActions;
    }

    public int coins() {
        return numCoins;
    }

    public int buys() {
        return numBuys;
    }

    public int victoryPointCount() {
        return victoryPointCount;
    }

    public boolean empty_on_cards() {
        return drawPile.isEmpty() && discardPile.isEmpty();
    }

    public boolean hasActions() {
        for (Card c : hand)
        {
            if (c instanceof Action)
                return true;
        }
        return false;
    }

    public Card peek_discard_pile() {
        return discardPile.get(discardPile.size() - 1);
    }

    public boolean draw(int times) {
        for (int i = 0 ; i < times ; ++i)
        {
            if (drawPile.isEmpty())
            {
                Collections.shuffle(discardPile);
                drawPile = discardPile;
                discardPile = new ArrayList<>();

                uih.display("Draw pile empty. Shuffling discard pile.", false);
            }
        
            if (!drawPile.isEmpty()) {
                Card drawnCard = drawPile.remove(0);
                hand.add(drawnCard);
                uih.display(name + " drew a " + drawnCard + ".", false);
            } else return false;
        }
        return true;
    }

    public void discard(Card card) {
        discardPile.add(card);
    }

    public void add_to_hand(Card card) {
        hand.add(card);
    }

    public void remove_from_hand(Card card) {
        hand.remove(card);
    }

    public void remove_from_discard(Card card) {
        discardPile.remove(card);
    }

    public void trash(Card card) {
        trash.add(card);
    }

    public void get_actions(int actions)
    {
        numActions += actions;
    }

    public void earn_coins(int coins)
    {
        numCoins += coins;
    }

    public void get_buys(int buys)
    {
        numBuys += buys;
    }

    public boolean buy_card(Card card)
    {
        if (numCoins >= card.cost())
        {
            numCoins -= card.cost();
            this.discardPile.add(card);

            return true;
        }
        return false;
    }

    public boolean gain(String card_name)
    {
        for (Supply<Card> s : kingdom.kingdom())
        {
            if (s.displayCard().toString().equals(card_name))
            {
                try {
                    discard(s.pull_card());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }
    public void gain(Card card)
    {
        discardPile.add(card);
    }


    @Override
    public String toString()
    {
        return name;
    }

}
