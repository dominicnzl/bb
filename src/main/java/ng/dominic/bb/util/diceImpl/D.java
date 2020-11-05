package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceValue;
import org.javatuples.Pair;

import java.util.Random;

public class D implements Dice {

    private final int faces;

    public D(int faces) {
        this.faces = faces;
    }

    @Override
    public int faces() {
        return faces;
    }

    @Override
    public Pair<Integer, DiceValue> roll() {
        var r = new Random();
        var result = 1 + r.nextInt(faces);
        return Pair.with(result, DiceValue.NUMERIC);
    }
}
