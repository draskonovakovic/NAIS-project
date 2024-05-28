package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;

public interface RefereeRepository extends Neo4jRepository<Referee, Long> {
    Referee findByEmail(String email);
}
