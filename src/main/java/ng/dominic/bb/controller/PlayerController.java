package ng.dominic.bb.controller;

import ng.dominic.bb.model.Player;
import ng.dominic.bb.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String playerListPage(Model model) {
        model.addAttribute("players", playerService.findAll());
        return "PlayerListPage.html";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        return ResponseEntity.of(playerService.findById(id));
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.TEXT_HTML_VALUE)
    public String playerEditPage(Model model, @PathVariable Long id) {
        if (playerService.findById(id).isEmpty()) {
            return "not found";
        } else {
            model.addAttribute("player", playerService.findById(id).get());
            return "PlayerEditPage.html";
        }
    }

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.save(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@RequestBody Player player, @PathVariable Long id) {
        var foundPlayer = playerService.findById(id);
        if (foundPlayer.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var updatedPlayer = copyValues(player, foundPlayer.get());
            return ResponseEntity.ok(playerService.update(updatedPlayer));
        }
    }

    private Player copyValues(Player player, Player temp) {
        temp.setName(player.getName());
        temp.setMovementAllowance(player.getMovementAllowance());
        temp.setStrength(player.getStrength());
        temp.setAgility(player.getAgility());
        temp.setArmourValue(player.getArmourValue());
        temp.setPassing(player.getPassing());
        return temp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        var foundPlayer = playerService.findById(id);
        if (foundPlayer.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            playerService.delete(foundPlayer.get());
            return ResponseEntity.noContent().build();
        }
    }
}
