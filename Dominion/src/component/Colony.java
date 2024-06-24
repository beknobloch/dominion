package component;

public class Colony extends Victory {

    @Override
    public int cost() { return 11; }

    @Override
    public int victory_points() { return 10; }

    @Override
    public String toString() { return "Colony"; }

}
