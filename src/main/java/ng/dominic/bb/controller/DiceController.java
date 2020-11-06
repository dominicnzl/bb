package ng.dominic.bb.controller;

import ng.dominic.bb.util.DiceFactory;
import ng.dominic.bb.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiceController {

    Logger logger = LoggerFactory.getLogger(DiceController.class);

    private final DiceFactory diceFactory;

    public DiceController(DiceFactory diceFactory) {
        this.diceFactory = diceFactory;
    }

    @GetMapping("/roll")
    public Result roll() {
        var d = DiceFactory.create("TrafficLight");
        logger.info("Dice created was: {}", d);
        return new Result(d);
    }
}
