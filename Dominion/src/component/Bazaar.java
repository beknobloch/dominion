package component;

public class Bazaar extends Action {

    @Override
    public int cost() { return 5; }

    @Override
    public int actions() { return 2; }
    @Override
    public int cards() { return 1; }
    @Override
    public int coin() { return 1; }

    @Override
    public String toString() { return "Bazaar"; }

}
