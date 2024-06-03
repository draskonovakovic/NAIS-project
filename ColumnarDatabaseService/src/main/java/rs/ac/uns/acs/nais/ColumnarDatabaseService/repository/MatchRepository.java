package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CassandraRepository<Match, Long> {
    //drugi upit
    @Query("select home_team_id from matches")
    List<Long> getHomeTeamsIds();

    @Query("select * from matches where id in :matchesIds")
    List<Match> getMoreInfoAboutMatches(@Param("matchesIds")List<Long> matchesIds);

    Optional<Match> findMatchByIdAndMatchDateTimeAndCity(long id, LocalDateTime date, String city);

    void deleteMatchByIdAndMatchDateTimeAndCity(long id, LocalDateTime date, String city);
}
