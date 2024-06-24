package component;

public class Village extends Action {

    @Override
    public int cost() { return 3; }

    @Override
    public int actions() { return 2; }
    @Override
    public int cards() { return 1; }

    @Override
    public String toString() { return "Village"; }

}
