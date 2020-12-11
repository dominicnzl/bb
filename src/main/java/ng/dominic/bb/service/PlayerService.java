package ng.dominic.bb.service;

import ng.dominic.bb.model.Player;
import ng.dominic.bb.repos.PlayerRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Optional<Player> findById(Long id) {
        return playerRepo.findById(id);
    }

    public Player getById(Long id) {
        return playerRepo.findById(id)
                .stream()
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
