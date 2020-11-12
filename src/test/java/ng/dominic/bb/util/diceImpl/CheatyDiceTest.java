package ng.dominic.bb.util.diceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheatyDiceTest {

    Logger logger = LoggerFactory.getLogger(CheatyDiceTest.class);

    @Test
    void faces() {
        assertEquals(6, new CheatyDice().getFaces());
    }

    @Test
    void roll() {
        var rolled = new CheatyDice().roll();
        var result = rolled.getValue0();
        logger.info(result == 6
                        ? "Rolled a {}, how lucky!"
                        : "Rolled a {}"
                , result);
        assertTrue(result >= 1 && result <= 6);
    }

    @Test
    @DisplayName("Are you sure you're supposed to roll so many 6s?")
    void many() {
        IntStream.range(0, 100).forEach(i -> roll());
    }
}