package component;

public class Laboratory extends Action {

    @Override
    public int cost() { return 5; }

    @Override
    public int actions() { return 1; }
    @Override
    public int cards() { return 2; }
    @Override
    public int coin() { return 0; }
    @Override
    public int buys() { return 0; }

    @Override
    public String toString() { return "Laboratory"; }

}
