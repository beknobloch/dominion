package component;

public class Duchy extends Victory {

    @Override
    public int cost() { return 5; }

    @Override
    public int victory_points() { return 3; }

    @Override
    public String toString() { return "Duchy"; }

}
