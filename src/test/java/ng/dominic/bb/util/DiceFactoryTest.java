package ng.dominic.bb.util;

import ng.dominic.bb.util.diceImpl.D6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DiceFactoryTest {

    @Autowired
    private DiceFactory diceFactory;

    @Test
    @DisplayName("DiceFactory should throw an IllegalArgument if the requested type is empty")
    void throwExceptionWhenNoDice() {
        assertThrows(IllegalArgumentException.class, () -> diceFactory.create(""));
    }

    @Test
    @DisplayName("DiceFactory should return correct dice when requested")
    void returnD6() {
        String simpleDice = D6.class.getSimpleName();
        assertTrue(diceFactory.create(simpleDice) instanceof D6);
    }
}