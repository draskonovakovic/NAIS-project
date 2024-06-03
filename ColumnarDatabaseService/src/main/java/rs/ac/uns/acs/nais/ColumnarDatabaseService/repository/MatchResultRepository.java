package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchResult;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchResultRepository extends CassandraRepository<MatchResult, Long>
{
    //Prvi upit
    @Query("SELECT player_of_game_id FROM match_results WHERE home_team_score <= 110 allow filtering")
    List<Long> getPlayerOfGameIdsForLowScoringGames();

    @Query("SELECT player_of_game_id FROM match_results")
    List<Long> getPlayerOfGameIds();

    void deleteMatchResultByIdAndMatchIdAndWinnerId(long id,long matchId,long winnerId);

    Optional<MatchResult> findMatchResultByIdAndMatchIdAndWinnerId(long id,long matchId,long winnerId);
}
