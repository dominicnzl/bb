package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;

import java.util.Random;

public class D6 implements Dice {
    @Override
    public int faces() {
        return 6;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        var outcome = 1 + r.nextInt(faces());
        return Pair.with(outcome, DiceValue.NUMERIC);
    }
}
