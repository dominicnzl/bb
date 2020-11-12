package ng.dominic.bb.util;

import org.javatuples.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private List<Pair<Integer, DiceValue>> values;

    private int sum;

    public Result(Dice... dice) {
        values = Arrays.stream(dice)
                .map(Dice::roll)
                .collect(Collectors.toList());
        sum = dice.length == 1
                ? values.get(0).getValue0()
                : getSumOfValue0();
    }

    // where value0 is the Integer in Pair<Integer, DiceValue>
    private int getSumOfValue0() {
        return values.stream()
                .mapToInt(Pair::getValue0)
                .reduce(0, Integer::sum);
    }

    public void clear() {
        values.clear();
    }

    public List<Pair<Integer, DiceValue>> getValues() {
        return values;
    }

    public void setValues(List<Pair<Integer, DiceValue>> values) {
        this.values = values;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
