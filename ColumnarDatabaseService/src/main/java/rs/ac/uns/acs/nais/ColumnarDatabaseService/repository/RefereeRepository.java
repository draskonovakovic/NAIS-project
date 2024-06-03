package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Referee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefereeRepository extends CassandraRepository<Referee, Long> {

    //treci upit
    @Query("SELECT id FROM referees WHERE birthday > '1980-01-01' allow filtering")
    List<Long> getYoungerRefereesIds();

    void deleteRefereeByIdAndName(long id,String name);

    Optional<Referee> findRefereeByIdAndName(long id, String name);
}
