package ng.dominic.bb.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ng.dominic.bb.util.Dice;
import ng.dominic.bb.util.DiceFactory;
import ng.dominic.bb.util.Result;
import ng.dominic.bb.util.diceImpl.CheatyDice;
import ng.dominic.bb.util.diceImpl.D100;
import ng.dominic.bb.util.diceImpl.D6;
import ng.dominic.bb.util.diceImpl.TrafficLightDice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiceController {

    Logger logger = LoggerFactory.getLogger(DiceController.class);

    private final DiceFactory diceFactory;

    public DiceController(DiceFactory diceFactory) {
        this.diceFactory = diceFactory;
    }

    @GetMapping("/roll/{chosenDice}")
    public Result roll(@PathVariable String chosenDice) {
        chosenDice = chosenDice == null || chosenDice.isEmpty()
                ? "D6"
                : chosenDice;
        var d = diceFactory.create(chosenDice);
        logger.info("Dice created was: {}", d);
        return new Result(d);
    }

    @GetMapping("/choices")
    public List<String> diceChoices() {
        return List.of(
                D6.class.getSimpleName(),
                D100.class.getSimpleName(),
                CheatyDice.class.getSimpleName(),
                TrafficLightDice.class.getSimpleName());
    }

    @PostMapping(value = "/roll", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public Result rickRoll(@RequestBody String json) {
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
                    .map(diceFactory::create)
                    .toArray(Dice[]::new);
            return new Result(dice);
        } catch (Exception e) {
            logger.warn("Input argument did not parse correctly, returned a D6 Result: {}", e.getMessage());
            return new Result(new D6());
        }
    }
}
