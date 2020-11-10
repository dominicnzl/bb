package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.DiceValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrafficLightDiceTest {

    Logger logger = LoggerFactory.getLogger(TrafficLightDice.class);

    @Test
    @DisplayName("The traffic light dice can have 3 states")
    void faces() {
        assertEquals(3, new TrafficLightDice().getFaces());
    }

    @Test
    void roll() {
        var rolled = new TrafficLightDice().roll();
        logger.info("Rolled: {}", rolled);
        assertTrue(Arrays.stream(DiceValue.values()).anyMatch(d -> d == rolled.getValue1()));
    }

    @Test
    void many() {
        IntStream.range(0, 100).forEach(i -> roll());
    }
}