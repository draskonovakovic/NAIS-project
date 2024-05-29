package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;

public interface MatchRepository extends Neo4jRepository<Match, Long> {
    Match findById(long id);
}
