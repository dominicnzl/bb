package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;

import java.util.Random;

public class D100 implements Dice {
    @Override
    public int faces() {
        return 100;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        return Pair.with(1 + r.nextInt(faces()), DiceValue.NUMERIC);
    }
}
