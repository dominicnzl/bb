package ng.dominic.bb.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ng.dominic.bb.util.DiceFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {

    Logger logger = LoggerFactory.getLogger(ResultTest.class);

    @Test
    @DisplayName("Result of null Dice")
    public void nullTest() {
        logger.info("Feeding null -> getValues is nonnull and sum is 0");
        var r = new Result();
        Assertions.assertNotNull(r.getValues());
        Assertions.assertEquals(0, r.getSum());
    }

    @Test
    @DisplayName("The result of a D6 roll should be one value between 1 and 6")
    public void D6Result() {
        var r = new Result(create("D6"));
        logger.info("The result has {} values", + r.getValues().length);
        assertEquals(1, r.getValues().length);

        int result = r.getValues()[0];
        logger.info("The result is {}", result);
        assertTrue(result >= 1 && result <= 6);
    }

    @Test
    @DisplayName("Testing two D6")
    public void _2D6Result() {
        var r = new Result(create("D6"), create("D6"));
        logger.info("The result has {} values", r.getValues().length);
        assertEquals(2, r.getValues().length);

        var sum = r.getSum();
        logger.info("The sum of the result is {}", sum);
        assertTrue(sum >= 2 && sum <= 12);

        for (int i = 0; i < r.getValues().length; i++) {
            var result = r.getValues()[i];
            logger.info("The result was {}", result);
            assertTrue(result >= 1 && result <= 6);
        }
    }

    @Test
    @DisplayName("D6Result should pass 100 times")
    public void manyD6() {
        logger.info("Testing D6");
        for (int i = 0; i < 100; i++) {
            D6Result();
        }
    }

    @Test
    @DisplayName("_2D6Result should pass 100 times")
    public void many2D6() {
        logger.info("Testing 2D6");
        for (int i = 0; i < 100; i++) {
            _2D6Result();
        }
    }
}