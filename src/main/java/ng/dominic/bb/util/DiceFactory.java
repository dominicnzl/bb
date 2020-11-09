package ng.dominic.bb.util;

import ng.dominic.bb.util.diceImpl.*;
import org.springframework.stereotype.Component;

@Component
public class DiceFactory {

    public static Dice create(String type) {
        return switch (type) {
            case "D6" -> new D6();
            case "D100" -> new D100();
            case "TrafficLightDice" -> new TrafficLightDice();
            case "CheatyDice" -> new CheatyDice();
            default -> throw new IllegalArgumentException("Type not specified");
        };
    }

    public static Dice d(int faces) {
        return new D(faces);
    }
}
