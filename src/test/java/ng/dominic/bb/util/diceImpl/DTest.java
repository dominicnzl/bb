package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class DTest {

    Logger logger = LoggerFactory.getLogger(DTest.class);

    static Dice d6;
    static Dice d20;
    static Dice d100;
    static List<Dice> collection;

    @BeforeAll
    static void init() {
        d6 = new D(6);
        d20 = new D(20);
        d100 = new D(100);
        collection = new ArrayList<>(List.of(d6, d20, d100));
    }

    @Test
    @DisplayName("Rolling many dice and testing possible outcomes")
    void many() {
        collection.forEach(dice -> IntStream.range(0, 100)
                .mapToObj(i -> dice)
                .forEach(this::roll)
        );
    }

    private void roll(Dice dice) {
        var rolled = dice.roll();
        var result = rolled.getValue0();
        var faces = dice.getFaces();
        logger.info("The dice roll is {} while the max value is {}", result, faces);
        Assertions.assertTrue(result >= 1 && result <= faces);
    }
}