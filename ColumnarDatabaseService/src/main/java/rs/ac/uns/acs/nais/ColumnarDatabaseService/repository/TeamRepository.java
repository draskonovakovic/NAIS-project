package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CassandraRepository<Team, Long> {

    //prvi upit i drugi
    @Query("SELECT * from teams" +
            " where id in :teamsIds")
    List<Team> getMoreInfoAboutTeams(@Param("teamsIds")List<Long> teamsIds);


    //Drugi prosti upit
    @Query("SELECT * from teams" +
            " where country = 'USA' allow filtering")
    List<Team> getTeamsFromUSA();

    @Query("SELECT id from teams" +
            " where country = 'USA' allow filtering")
    List<Long> getTeamsFromUSAIds();

    Optional<Team> findTeamByIdAndName(long id, String name);

    void deleteTeamByIdAndName(long id,String name);
}
