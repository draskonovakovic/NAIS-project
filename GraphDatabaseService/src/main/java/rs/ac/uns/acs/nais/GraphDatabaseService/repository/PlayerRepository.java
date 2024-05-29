package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;

public interface PlayerRepository extends Neo4jRepository<Player, Long> {
    Player findByEmail(String email);
}
