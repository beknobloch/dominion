package component;

public enum KingdomOption {

    CLASSIC(1, "Classic");

    private final int index;
    private final String printableName;

    private KingdomOption(int index, String printableName) {
        this.index = index;
        this.printableName = printableName;
    }

    public static KingdomOption get(int index) throws Exception
    {
        for (KingdomOption ko : KingdomOption.values())
        {
            if (ko.index == index)
                return ko;
        }
        throw new Exception("There is no Kingdom Option corresponding to that index.");
    }

    @Override
    public String toString() {
        return printableName;
    }

}
