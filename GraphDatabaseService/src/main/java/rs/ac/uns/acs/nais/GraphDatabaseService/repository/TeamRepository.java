package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;

public interface TeamRepository extends Neo4jRepository<Team, Long> {
    Team findByEmail(String email);
}
