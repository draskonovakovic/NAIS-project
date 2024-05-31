package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;

public interface TeamRepository extends Neo4jRepository<Team, Long> {
    Team findByEmail(String email);

    @Query("MATCH (t:Team {id: $teamId}) " +
            "            MATCH (m:Match {id: $matchId}) " +
            "            CREATE (t)-[playsMatch:PLAYS_MATCH]->(m) " +
            "            SET playsMatch.teamSide = $teamSide, playsMatch.won = $won")
    void createPlay(Long teamId, Long matchId, String teamSide, Boolean won);
}
