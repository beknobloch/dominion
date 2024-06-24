package component;

public enum Type {
    Victory,
    Curse,
    Treasure,
    Action,
    Attack,
    Reaction;

    private final String printableName;

    private Type(String printableName) {
        this.printableName = printableName;
    }

    @Override
    public String toString() {
        return printableName;
    }
}
