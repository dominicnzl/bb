package ng.dominic.bb.util.diceImpl;

import ng.dominic.bb.util.Dice;

import java.util.Random;

public class D100 implements Dice {
    @Override
    public int faces() {
        return 100;
    }

    @Override
    public int roll() {
        var r = new Random();
        return 1 + r.nextInt(faces());
    }
}
