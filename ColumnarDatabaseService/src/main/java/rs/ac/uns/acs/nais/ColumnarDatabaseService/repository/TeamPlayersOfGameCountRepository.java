package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.TeamPlayersOfGameCount;

import java.util.List;

public interface TeamPlayersOfGameCountRepository extends CassandraRepository<TeamPlayersOfGameCount, Long> {
    //prvi upit
    @Query("SELECT team_id, COUNT(*) from players" +
            " where id in :playersOfGames and country in ('Germany','Spain','Japan','USA')" +
            " group by team_id allow filtering")
    List<TeamPlayersOfGameCount> countPlayersOfGameByTeams(@Param("playersOfGames") List<Long> playersOfGames);

    @Query("SELECT team_id, COUNT(*) from players" +
            " where id in :playersOfGames and country in ('USA')" +
            " group by team_id allow filtering")
    List<TeamPlayersOfGameCount> countPlayersOfGameByTeamsFromUSA(@Param("playersOfGames") List<Long> playersOfGames);

}
