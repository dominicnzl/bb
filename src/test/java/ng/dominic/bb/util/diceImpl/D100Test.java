package ng.dominic.bb.util.diceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class D100Test {

    Logger logger = LoggerFactory.getLogger(D100Test.class);

    @Test
    @DisplayName("D100 should have 100 faces")
    void faces() {
        var faces = new D100().getFaces();
        logger.info("D100 has {} faces", faces);
        assertEquals(100, faces);
    }

    @Test
    @DisplayName("The value range of a D100 is between 1 and 100 inclusive")
    void roll() {
        var result = new D100().roll().getValue0();
        logger.info("The result was {}", result);
        assertTrue(result >= 1 && result <= 100);
    }
}