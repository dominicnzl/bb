package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;

import java.util.Random;

/**
 * This die has 10% chance of rolling any value from 1 to 5 and 40% chance of rolling 6. ;)
 */
public class CheatyDice implements Dice {
    @Override
    public int faces() {
        return 6;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        var result = 1 + r.nextInt(10);
        return Pair.with(Math.min(result, 6), DiceValue.NUMERIC);
    }
}
