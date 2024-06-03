package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends CassandraRepository<Player, Long> {

    //prost upit 1
    @Query("SELECT * from players where height > 6.5 and weight > 210 and birthday > '1992-01-01' allow filtering")
    List<Player> filterPlayersByHeightWeightAndBirthday();

    @Query("SELECT * from players" +
            " where country = 'USA' allow filtering")
    List<Player> getPlayersFromUSA();

    Optional<Player> findPlayerByIdAndAndTeamId(long id,long teamId);
    void deletePlayerByIdAndTeamId(long id,long teamId);
}
