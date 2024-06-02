package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Hall;

import java.util.List;

public interface HallRepository extends Neo4jRepository<Hall, Long> {

    @Query("MATCH (t:Team {id: $teamId})-[pm:PLAYS_MATCH {teamSide: 'home'}]->(m:Match) " +
            "WITH t, avg(m.attendance) AS avgAttendance " +
            "MATCH (h:Hall) " +
            "WHERE h.city = t.city AND h.capacity >= avgAttendance " +
            "RETURN h " +
            "ORDER BY h.capacity ASC " +
            "LIMIT 3")
    public List<Hall> recommendHallsForTeam(Long teamId);

    @Query("MATCH (n) DETACH DELETE n")
    public void deleteAll();
}
