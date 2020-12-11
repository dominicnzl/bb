package ng.dominic.bb.repos;

import ng.dominic.bb.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerRepoIntegrationTest {

    @Autowired
    private PlayerRepo playerRepo;

    @Test
    public void whenCalledSave_thenCorrectNumberOfPlayers() {
        Player morg = new Player();
        morg.setName("Morg");
        morg.setMovementAllowance(4);
        morg.setStrength(5);
        morg.setAgility(5);
        morg.setPassing(6);
        morg.setArmourValue(10);
        morg.setPrice(240);
        playerRepo.save(morg);
        List<Player> players = playerRepo.findAll();

        assertTrue(players.contains(morg));
    }
}