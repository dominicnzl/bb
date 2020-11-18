package ng.dominic.bb.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.Result;
import ng.dominic.bb.util.diceImpl.D6;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping(value = "/roll", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public Result roll(@RequestBody String json) {
        try {
            var objectMapper = new ObjectMapper();
            var rootNode = objectMapper.readTree(json);
            var diceNode = rootNode.get("dice");
            var chosenDice = new ArrayList<String>();
            if (diceNode.isArray()) {
                for (JsonNode die : diceNode) {
                    logger.info("Dice created was: {}", die);
                    chosenDice.add(die.textValue());
                }
            }
            Dice[] dice = chosenDice.stream()
                    .map(diceMap::get)
                    .toArray(Dice[]::new);
            return new Result(dice);
        } catch (Exception e) {
            logger.warn("Input argument did not parse correctly, returned a D6 Result: {}", e.getMessage());
            return new Result(new D6());
        }
    }
}
