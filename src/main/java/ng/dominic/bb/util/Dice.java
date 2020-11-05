package ng.dominic.bb.util;

import java.util.Map;

public interface Dice {

    /**
     * @return Dice have a non-zero number of faces. For example, a common die has 6 faces with values 1 to 6 inclusive.
     */
    int faces();

    /**
     * @return The index of the die face, counting from 1. With normal dice, the index of the face equals the value.
     */
    int roll();

    // TODO: 05/11/2020 -> still have to think about how to partition the face and the value of a roll. Perhaps the roll should return a valuepair instead of int
    default Map<Integer, String> value(int rolled) {
        return Map.of(rolled, String.valueOf(rolled));
    }
}
