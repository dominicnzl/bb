package ng.dominic.bb.controller;

import ng.dominic.bb.exceptions.PlayerNotFoundException;
import ng.dominic.bb.model.Player;
import ng.dominic.bb.model.PlayerDTO;
import ng.dominic.bb.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;
    private final ModelMapper modelMapper;

    public PlayerController(PlayerService playerService, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.modelMapper = modelMapper;
    }

    private PlayerDTO convertToDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    private Player convertToEntity(PlayerDTO dto) {
        return modelMapper.map(dto, Player.class);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll() {
        var dto = playerService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String playerListPage(Model model) {
        model.addAttribute("players", playerService.findAll());
        return "PlayerListPage.html";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable Long id) {
        var dto = playerService.findById(id).map(this::convertToDTO);
        return ResponseEntity.of(dto);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.TEXT_HTML_VALUE)
    public String playerEditPage(Model model, @PathVariable Long id) {
        var player = playerService.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        model.addAttribute("player", player);
        return "PlayerEditPage.html";
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO dto) {
        var entity = convertToEntity(dto);
        var savedEntity = playerService.save(entity);
        return ResponseEntity.ok(convertToDTO(savedEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@RequestBody PlayerDTO dto, @PathVariable Long id) {
        if (playerService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var entity = convertToEntity(dto);
        var updatedEntity = playerService.update(entity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
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
