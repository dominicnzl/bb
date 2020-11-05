package ng.dominic.bb.util;

import ng.dominic.bb.util.diceImpl.D100;
import ng.dominic.bb.util.diceImpl.D6;
import org.springframework.stereotype.Component;

@Component
public class DiceFactory {

    public static Dice create(String type) {
        return switch (type) {
            case "D6" -> new D6();
            case "D100" -> new D100();
            default -> throw new IllegalArgumentException("Type not specified");
        };
    }
}
