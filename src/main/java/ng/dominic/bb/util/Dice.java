package ng.dominic.bb.util;

import org.javatuples.Pair;

public interface Dice {

    /**
     * @return Dice have a non-zero number of faces. For example, a common die has 6 faces with values 1 to 6 inclusive.
     */
    int faces();

    /**
     * @return The index of the die face, counting from 1. With normal dice, the index of the face equals the value.
     */
    Pair<Integer, DiceValue> roll();
}
