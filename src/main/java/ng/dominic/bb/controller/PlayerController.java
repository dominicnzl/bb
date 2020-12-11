package ng.dominic.bb.controller;

import ng.dominic.bb.model.Player;
import ng.dominic.bb.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/{id}")
    public Player hallo(@PathVariable Long id) {
        return playerService.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Player by this id: %s does not exist", id)));
    }
}
