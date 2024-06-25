package component;

import java.util.ArrayList;

public class Kingdom {

    ArrayList<Supply<Card>> kingdom;

    public Kingdom(int playerCount)
    {
        this.kingdom = new ArrayList<>();

<<<<<<< Updated upstream
        int victoryCardCount = playerCount <= 2 ? 8 : 12;
        
        kingdom.add(new Supply<>(victoryCardCount, Estate::new));
        kingdom.add(new Supply<>(victoryCardCount, Duchy::new));
        kingdom.add(new Supply<>(victoryCardCount, Province::new));
=======
        kingdom.add(new Supply<>(20, Curse::new));
        kingdom.add(new Supply<>(20, Estate::new));
        kingdom.add(new Supply<>(10, Duchy::new));
        kingdom.add(new Supply<>(12, Province::new));
>>>>>>> Stashed changes

        kingdom.add(new Supply<>(60, Copper::new));
        kingdom.add(new Supply<>(40, Silver::new));
        kingdom.add(new Supply<>(30, Gold::new));

    }
    public Kingdom(int playerCount, UserInputHandler uih, KingdomOption ko, boolean useExpandedBase)
    {
        this(playerCount);

        if (useExpandedBase) {
            kingdom.add(new Supply<>(12, Platinum::new));
            kingdom.add(new Supply<>(playerCount <= 2 ? 8 : 12, Colony::new));
        }

        switch (ko) {

            case CLASSIC -> {

                kingdom.add(new Supply<>(10, () -> new Cellar()));
                kingdom.add(new Supply<>(10, () -> new Market()));
                kingdom.add(new Supply<>(10, () -> new Militia()));
                kingdom.add(new Supply<>(10, () -> new Moat()));
                kingdom.add(new Supply<>(10, () -> new Smithy()));
                kingdom.add(new Supply<>(10, () -> new Village()));

            }

            default -> {

                kingdom.add(new Supply<>(10, () -> new Cellar()));

            }
        }
    }

    public ArrayList<Supply<Card>> kingdom() {
        return kingdom;
    }

    public int numEmptySupplyPiles() {
        int count = 0;
        for (Supply<Card> s : kingdom)
        {
            if (s.empty())  ++count;
        }
        return count;
    }
    public boolean emptyOnProvinces() {
        for (Supply<Card> s : kingdom)
        {
            if (s.displayCard() instanceof Province && s.empty())
                return true;
        }
        return false;
    }

}
