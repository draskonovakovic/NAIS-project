package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;

public interface PlayerRepository extends Neo4jRepository<Player, Long> {
    Player findByEmail(String email);

    @Query("MATCH (p:Player {id: $playerId}) " +
            "MATCH (t:Team {id: $teamId}) " +
            "CREATE (p)-[playsFor:PLAYS_FOR]->(t) " +
            "SET playsFor.isPlaying = true, playsFor.jerseyNumber = $jerseyNumber")
    void setPlaysFor(Long playerId, Long teamId, int jerseyNumber);
}
