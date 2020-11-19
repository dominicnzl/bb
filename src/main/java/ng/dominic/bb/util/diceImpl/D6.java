package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;

import java.util.Random;

public class D6 extends Dice {

    @Override
    public int getFaces() {
        return 6;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        var outcome = 1 + r.nextInt(getFaces());
        return Pair.with(outcome, DiceValue.NUMERIC);
    }
}
