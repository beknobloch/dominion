package component;

public class Gold extends Treasure {

    @Override
    public int cost() { return 6; }

    @Override
    public int value() { return 3; }

    @Override
    public String toString() { return "Gold"; }

}
