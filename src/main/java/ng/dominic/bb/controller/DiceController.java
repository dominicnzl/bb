package ng.dominic.bb.controller;

import com.jayway.jsonpath.JsonPath;
import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class DiceController {

    Logger logger = LoggerFactory.getLogger(DiceController.class);

    private final Map<String, Dice> diceMap;

    public DiceController(Map<String, Dice> diceMap) {
        this.diceMap = diceMap;
    }

    @GetMapping("/roll/{chosenDice}")
    public Result rollSpecific(@PathVariable String chosenDice) {
        chosenDice = chosenDice == null || chosenDice.isEmpty()
                ? "D6"
                : chosenDice;
        var d = diceMap.get(chosenDice);
        logger.info("Dice created was: {}", d);
        return new Result(d);
    }

    @GetMapping("/choices")
    public Set<String> diceChoices() {
        return diceMap.keySet();
    }

    @PostMapping(value = "/roll")
    public Result roll(@RequestBody String json) {
        try {
            List<String> diceNames = JsonPath.read(json, "$.dice[*]");
            var dice = diceNames.stream()
                    .map(diceMap::get)
                    .toArray(Dice[]::new);
            return new Result(dice);
        } catch (Exception e) {
            logger.warn("Input argument did not parse correctly, returned a D6 Result: {}", e.getMessage());
            return new Result(diceMap.get("d6"));
        }
    }
}
