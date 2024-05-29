package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;

public interface RefereeRepository extends Neo4jRepository<Referee, Long> {
    Referee findByEmail(String email);

    @Query("MATCH (r:Referee {idOriginal: $refereeId}) \" +\n" +
            "            \"MATCH (m:Match {idOriginal: $matchId}) \" +\n" +
            "            \"CREATE (c)-[refereed:REFEREED]->(p) \" +\n" +
            "            \"SET refereed.points = points, refereed.isRisk = isRisk")
    void createRefereed(Long refereeId, Long matchId, int points, boolean isRisk);
}
