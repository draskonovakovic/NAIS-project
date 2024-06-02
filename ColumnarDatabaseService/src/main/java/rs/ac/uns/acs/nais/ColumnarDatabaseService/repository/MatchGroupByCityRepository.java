package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchesGroupByCity;

import java.util.List;

public interface MatchGroupByCityRepository extends CassandraRepository<MatchesGroupByCity,Long> {
    //treci upit
    @Query("SELECT city,count(*) from" +
            " matches where referee_id in :refereeIds and match_date_time > '2023-05-22' " +
            " group by city allow filtering")
    List<MatchesGroupByCity> groupMatchesByCity(@Param("refereeIds")List<Long> refereeIds);
}
