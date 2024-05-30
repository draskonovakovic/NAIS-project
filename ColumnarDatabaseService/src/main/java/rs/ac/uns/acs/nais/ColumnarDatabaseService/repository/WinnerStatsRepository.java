package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.WinnerStats;

import java.util.List;

public interface WinnerStatsRepository extends CassandraRepository<WinnerStats, Long> {
    //drugi upit
    @Query("SELECT winner_id, avg(home_team_score) as avgScore, avg(home_team_rebounds) as avgRebounds, avg(home_team_turnovers) as avgTurnovers" +
            " from match_results" +
            " where winner_id in :homeTeams group by winner_id allow filtering")
    List<WinnerStats> getWinnerStats(@Param("homeTeams") List<Long> homeTeams);
}
