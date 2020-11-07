package ng.dominic.bb.util;

import org.javatuples.Pair;

public abstract class Dice {

    protected int faces;

    /**
     * @return Dice have a non-zero number of faces. For example, a common die has 6 faces with values 1 to 6 inclusive.
     */
    public abstract int getFaces();

    /**
     * @return The index of the die face, counting from 1. With normal dice, the index of the face equals the value.
     */
    public abstract Pair<Integer, DiceValue> roll();
}
