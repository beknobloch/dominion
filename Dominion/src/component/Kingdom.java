package component;

import java.util.ArrayList;

public class Kingdom {

    ArrayList<Supply<Card>> kingdom;

    public Kingdom()
    {
        this.kingdom = new ArrayList<>();

        kingdom.add(new Supply<>(20, Estate::new));
        kingdom.add(new Supply<>(10, Duchy::new));
        kingdom.add(new Supply<>(12, Province::new));

        kingdom.add(new Supply<>(30, Copper::new));
        kingdom.add(new Supply<>(30, Silver::new));
        kingdom.add(new Supply<>(30, Gold::new));

    }
    public Kingdom(UserInputHandler uih, KingdomOption ko)
    {
        this();

        switch (ko) {

            case CLASSIC -> {

                kingdom.add(new Supply<>(10, () -> new Cellar(uih)));

            }

            default -> {

                kingdom.add(new Supply<>(10, () -> new Cellar(uih)));

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
