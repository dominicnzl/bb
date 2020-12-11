package ng.dominic.bb.service;

import ng.dominic.bb.model.Player;
import ng.dominic.bb.repos.PlayerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerService {

    private PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    public Optional<Player> findById(Long id) {
        return playerRepo.findById(id);
    }

    public Player save(Player player) {
        return playerRepo.save(player);
    }

    public Player update(Player player) {
        return playerRepo.save(player);
    }

    public void delete(Player player) {
        playerRepo.delete(player);
    }
}
