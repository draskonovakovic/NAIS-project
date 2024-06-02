package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;

import java.util.List;

public interface TeamRepository extends Neo4jRepository<Team, Long> {
    Team findByEmail(String email);

    @Query("MATCH (t:Team {id: $teamId}) " +
            "            MATCH (m:Match {id: $matchId}) " +
            "            CREATE (t)-[playsMatch:PLAYS_MATCH]->(m) " +
            "            SET playsMatch.teamSide = $teamSide, playsMatch.won = $won")
    void createPlay(Long teamId, Long matchId, String teamSide, Boolean won);
    
    @Query("MATCH (r:Referee {idOriginal: $refereeId})-[refereed:REFEREED]->(m:Match)<-[:PLAYS_MATCH]-(t:Team) " +
            "WHERE refereed.points > 4 " +
            "WITH t, COUNT(m) AS match_count " +
            "ORDER BY match_count DESC " +
            "LIMIT 4 " +
            "RETURN t")
    List<Team> recommendTeamsForReferee(Long refereeId);

    @Query("MATCH (t:Team)-[:PLAYS_MATCH]->(:Match {id: $matchId}) " +
            "RETURN t")
    List<Team> getTeamsForMatch(Long matchId);

    @Query("MATCH (:Team {id: $teamId})-[:PLAYS_MATCH {teamSide: 'home'}]->(m:Match) " +
            "RETURN avg(m.attendance)")
    Double getTeamAvgAttendanceHome(Long teamId);
}
