package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;

import java.time.LocalDateTime;
import java.util.List;

public interface RefereeRepository extends Neo4jRepository<Referee, Long> {
    Referee findByEmail(String email);

    @Query("MATCH (r:Referee {idOriginal: $refereeId}) " +
            "            MATCH (m:Match {id: $matchId}) " +
            "            CREATE (r)-[refereed:REFEREED]->(m) " +
            "            SET refereed.points = $points, refereed.isRisk = $isRisk")
    void createRefereed(Long refereeId, Long matchId, int points, boolean isRisk);

    @Query("MATCH (currentMatch:Match {id: $matchId, matchDay: $matchDay}) " +
            "MATCH (referee:Referee)-[:REFEREED]->(previousMatch:Match) " +
            "WHERE previousMatch.matchDay < currentMatch.matchDay " +
            "  AND NOT (referee)-[:REFEREED]->(currentMatch) " +
            "WITH referee, COUNT(previousMatch) AS experience " +
            "WHERE experience > 2 " +
            "RETURN referee " +
            "ORDER BY experience DESC " +
            "LIMIT 4")
    List<Referee> recommendRefereeByExperience(Long matchId, LocalDateTime matchDay);

    @Query("MATCH (currentMatch:Match {id: $matchId, matchDay: $matchDay}) " +
            "MATCH (referee:Referee)-[rel:REFEREED]->(previousMatch:Match) " +
            "WHERE previousMatch.matchDay < currentMatch.matchDay " +
            "             AND NOT (referee)-[:REFEREED]->(currentMatch) " +
            "WITH referee, SUM(rel.points) AS totalPoints " +
            "WHERE totalPoints > $avgPoints " +
            "RETURN referee " +
            "ORDER BY totalPoints DESC " +
            "LIMIT 4")
    List<Referee> recommendRefereesByAvgPoints(Long matchId, LocalDateTime matchDay, double avgPoints);

    @Query("MATCH (currentMatch:Match {id: $matchId, matchDay: $matchDay}) " +
            "MATCH (otherReferee:Referee)-[otherRel:REFEREED]->(otherPreviousMatch:Match) " +
            "WHERE otherPreviousMatch.matchDay < currentMatch.matchDay " +
            "  AND NOT (otherReferee)-[:REFEREED]->(currentMatch) " +
            "WITH COALESCE(AVG(otherRel.points), 0) AS avgPoints " +
            "RETURN avgPoints ")
    double avgPoints(Long matchId, LocalDateTime matchDay);
}
