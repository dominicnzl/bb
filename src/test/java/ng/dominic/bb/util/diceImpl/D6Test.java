package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.DiceValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class D6Test {

    Logger logger = LoggerFactory.getLogger(D6Test.class);

    @Test
    @DisplayName("The number of faces should be 6")
    void faces() {
        assertEquals(6, new D6().getFaces());
    }

    @Test
    @DisplayName("The result should be between 1 and 6 and the DiceValue should be Numeric")
    void roll() {
        var rolled = new D6().roll();
        var result = rolled.getValue0();
        var value = rolled.getValue1();
        logger.info("Dice roll was {} and the value is {}", result, value);
        assertTrue(result >= 1 && result <= 6);
        assertSame(value, DiceValue.NUMERIC);
    }

    @Test
    @DisplayName("roll() should pass when done 100 times")
    void many() {
        for (int i = 0; i < 100; i++) {
            roll();
        }
    }
}