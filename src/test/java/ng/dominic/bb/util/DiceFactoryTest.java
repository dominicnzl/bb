package ng.dominic.bb.util;

import ng.dominic.bb.util.diceImpl.D6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiceFactoryTest {

    @Test
    @DisplayName("DiceFactory should throw an IllegalArgument if the requested type is empty")
    void throwExceptionWhenNoDice() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DiceFactory.create(""));
    }

    @Test
    @DisplayName("DiceFactory should return correct dice when requested")
    void returnD6() {
        Assertions.assertTrue(DiceFactory.create("D6") instanceof D6);
    }
}