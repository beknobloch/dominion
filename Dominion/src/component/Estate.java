package component;

public class Estate extends Victory {

    @Override
    public int cost() { return 2; }

    @Override
    public int victory_points() { return 1; }

    @Override
    public String toString() { return "Estate"; }

}
