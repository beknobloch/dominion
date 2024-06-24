package component;

public class Festival extends Action {

    @Override
    public int cost() { return 5; }

    @Override
    public int actions() { return 2; }
    @Override
    public int coin() { return 2; }
    @Override
    public int buys() { return 1; }

    @Override
    public String toString() { return "Festival"; }

}
