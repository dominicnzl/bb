package ng.dominic.bb.controller;

import ng.dominic.bb.util.DiceFactory;
import ng.dominic.bb.util.Result;
import ng.dominic.bb.util.diceImpl.CheatyDice;
import ng.dominic.bb.util.diceImpl.D100;
import ng.dominic.bb.util.diceImpl.D6;
import ng.dominic.bb.util.diceImpl.TrafficLightDice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        var d = DiceFactory.create(chosenDice);
        logger.info("Dice created was: {}", d);
        return new Result(d);
    }

    @GetMapping("/choices")
    public List<String> diceChoices() {
        return List.of(
                CheatyDice.class.getSimpleName(),
                D6.class.getSimpleName(),
                D100.class.getSimpleName(),
                TrafficLightDice.class.getSimpleName());
    }
}
