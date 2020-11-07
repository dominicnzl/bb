package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;

import java.util.Random;

public class D100 extends Dice {

    @Override
    public int getFaces() {
        return 100;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        return Pair.with(1 + r.nextInt(getFaces()), DiceValue.NUMERIC);
    }
}
