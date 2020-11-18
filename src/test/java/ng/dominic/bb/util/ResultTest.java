package ng.dominic.bb.util;

import ng.dominic.bb.util.diceImpl.D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestConstructor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Note to self: dependency injection via constructor is an opt-in which can be enabled when this annotation is added to
 * ResultTest: @TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL). Alternatively, the argument can be
 * passed to the Run Configuration or embedded in the pom. Doing this would allow us to do this:
 *
 *     public ResultTest(Map<String, Dice> diceMap, ApplicationContext applicationContext) {
 *         this.diceMap = diceMap;
 *         this.applicationContext = applicationContext;
 *     }
 *
 * For now, I will leave the autowiring by field as is.
 */
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ResultTest {

    Logger logger = LoggerFactory.getLogger(ResultTest.class);

    @Autowired
    private Map<String, Dice> diceMap;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    @DisplayName("Result of null Dice")
    public void nullTest() {
        logger.info("Feeding null -> getValues is nonnull and sum is 0");
        var r = new Result();
        assertNotNull(r.getValues());
        assertEquals(0, r.getSum());
    }

    @Test
    @DisplayName("The result of a D6 roll should be one value between 1 and 6")
    public void D6Result() {
        // calling the DiceConfig method d(6). Alternatively I could call diceMap.get("d6) which calls the d6() method.
        var d = applicationContext.getBean(D.class, 6);
        logger.info("Got this dice with {} faces", d.faces);

        var r = new Result(d);
        var size = r.getValues().size();
        logger.info("The result has {} values", + size);
        assertEquals(1, size);

        int result = r.getValues().get(0).getValue0();
        logger.info("The result is {}", result);
        assertTrue(result >= 1 && result <= 6);
    }

    @Test
    @DisplayName("Testing two D6")
    public void _2D6Result() {
        var r = new Result(diceMap.get("d6"), diceMap.get("d6"));
        var size = r.getValues().size();
        logger.info("The result has {} values", size);
        assertEquals(2, size);

        var sum = r.getSum();
        logger.info("The sum of the result is {}", sum);
        assertTrue(sum >= 2 && sum <= 12);

        for (int i = 0; i < size; i++) {
            var result = r.getValues().get(i).getValue0();
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

    @Test
    @DisplayName("After clearing the Result should have no values")
    public void clearTest() {
        var result = new Result(diceMap.get("d6"), diceMap.get("d6"));
        assertTrue(result.getValues().size() > 0);
        result.clear();
        assertTrue(result.getValues().isEmpty());
    }
}