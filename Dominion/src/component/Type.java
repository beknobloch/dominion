package component;

public enum Type {
    VICTORY("Victory"),
    CURSE("Curse"),
    TREASURE("Treasure"),
    ACTION("Action"),
    ATTACK("Attack"),
    REACTION("Reaction");

    private final String printableName;

    private Type(String printableName) {
        this.printableName = printableName;
    }

    @Override
    public String toString() {
        return printableName;
    }
}
