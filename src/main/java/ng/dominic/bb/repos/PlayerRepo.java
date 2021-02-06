package ng.dominic.bb.repos;

import ng.dominic.bb.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
}
