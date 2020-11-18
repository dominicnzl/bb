package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TrafficLightDice extends Dice {

    @Override
    public int getFaces() {
        return 3;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        var result = 1 + r.nextInt(3);
        var value = switch (result) {
            case 1 -> DiceValue.RED;
            case 2 -> DiceValue.YELLOW;
            case 3 -> DiceValue.GREEN;
            default -> throw new IllegalStateException("Unexpected value: " + result);
        };
        return Pair.with(result, value);
    }
}
