package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Match;

import java.util.UUID;

@Repository
public interface MatchRepository extends CassandraRepository<Match, UUID> {
}
