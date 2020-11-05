package ng.dominic.bb.util;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Result {

    private int[] values;

    private int sum;

    public Result(Dice... dice) {
        values = Arrays.stream(dice)
                .map(Dice::roll)
                .flatMapToInt(IntStream::of)
                .toArray();
        sum = dice.length == 1 ? values[0] : Arrays.stream(values).sum();
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
