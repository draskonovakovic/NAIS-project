package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Referee;

import java.util.UUID;

@Repository
public interface RefereeRepository extends CassandraRepository<Referee, UUID> {
}
