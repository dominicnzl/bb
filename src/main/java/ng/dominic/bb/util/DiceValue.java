package ng.dominic.bb.util;

public enum DiceValue {
    NUMERIC,
    RED,
    YELLOW,
    GREEN;

    private int numericValue;

    DiceValue() {
    }

    DiceValue(int numericValue) {
        this.numericValue = numericValue;
    }

    public int getNumericValue() {
        return numericValue;
    }
}
