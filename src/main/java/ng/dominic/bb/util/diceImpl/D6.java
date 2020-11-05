package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;

import java.util.Random;

public class D6 implements Dice {
    @Override
    public int faces() {
        return 6;
    }

    @Override
    public int roll() {
        var r = new Random();
        return 1 + r.nextInt(faces());
    }
}
