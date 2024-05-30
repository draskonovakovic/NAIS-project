package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;

import java.util.List;

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
}
