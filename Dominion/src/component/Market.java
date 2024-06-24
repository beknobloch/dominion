package component;

public class Market extends Action {

    @Override
    public int cost() { return 5; }

    @Override
    public int actions() { return 1; }
    @Override
    public int cards() { return 1; }
    @Override
    public int coin() { return 1; }
    @Override
    public int buys() { return 1; }

    @Override
    public String toString() { return "Market"; }

}
