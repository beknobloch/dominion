package component;

public class Copper extends Treasure {

    @Override
    public int value() { return 1; }

    @Override
    public int cost() { return 0; }

    @Override
    public String toString() { return "Copper"; }

}