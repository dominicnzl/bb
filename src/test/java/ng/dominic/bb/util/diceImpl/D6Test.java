package ng.dominic.bb.util.diceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class D6Test {

    Logger logger = LoggerFactory.getLogger(D6Test.class);

    @Test
    @DisplayName("The number of faces should be 6")
    void faces() {
        Assertions.assertEquals(6, new D6().faces());
    }

    @Test
    @DisplayName("The result should be between 1 and 6")
    void roll() {
        var result = new D6().roll().getValue0();
        logger.info("Dice roll was {}", result);
        Assertions.assertTrue(result >= 1 && result <= 6);
    }

    @Test
    @DisplayName("roll() should pass when done 100 times")
    void many() {
        for (int i = 0; i < 100; i++) {
            roll();
        }
    }
}