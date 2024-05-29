package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table("match_results")
public class MatchResult {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @Column("match_id")
    private UUID matchId;

    @Column("winner_id")
    private UUID winnerId;

    @Column("home_team_score")
    private int homeTeamScore;

    @Column("away_team_score")
    private int awayTeamScore;

    @Column("player_of_game_id")
    private UUID playerOfGameId;

    @Column("home_team_field_goals")
    private int homeTeamFieldGoals;

    @Column("home_team_three_point_field_goals")
    private int homeTeamThreePointFieldGoals;

    @Column("home_team_free_throws")
    private int homeTeamFreeThrows;

    @Column("away_team_field_goals")
    private int awayTeamFieldGoals;

    @Column("away_team_three_point_field_goals")
    private int awayTeamThreePointFieldGoals;

    @Column("away_team_free_throws")
    private int awayTeamFreeThrows;

    @Column("home_team_rebounds")
    private int homeTeamRebounds;

    @Column("away_team_rebounds")
    private int awayTeamRebounds;

    @Column("home_team_turnovers")
    private int homeTeamTurnovers;

    @Column("away_team_turnovers")
    private int awayTeamTurnovers;

    @Column("home_team_personal_fouls")
    private int homeTeamPersonalFouls;

    @Column("away_team_personal_fouls")
    private int awayTeamPersonalFouls;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public UUID getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(UUID winnerId) {
        this.winnerId = winnerId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public UUID getPlayerOfGameId() {
        return playerOfGameId;
    }

    public void setPlayerOfGameId(UUID playerOfGameId) {
        this.playerOfGameId = playerOfGameId;
    }

    public int getHomeTeamFieldGoals() {
        return homeTeamFieldGoals;
    }

    public void setHomeTeamFieldGoals(int homeTeamFieldGoals) {
        this.homeTeamFieldGoals = homeTeamFieldGoals;
    }

    public int getHomeTeamThreePointFieldGoals() {
        return homeTeamThreePointFieldGoals;
    }

    public void setHomeTeamThreePointFieldGoals(int homeTeamThreePointFieldGoals) {
        this.homeTeamThreePointFieldGoals = homeTeamThreePointFieldGoals;
    }

    public int getHomeTeamFreeThrows() {
        return homeTeamFreeThrows;
    }

    public void setHomeTeamFreeThrows(int homeTeamFreeThrows) {
        this.homeTeamFreeThrows = homeTeamFreeThrows;
    }

    public int getAwayTeamFieldGoals() {
        return awayTeamFieldGoals;
    }

    public void setAwayTeamFieldGoals(int awayTeamFieldGoals) {
        this.awayTeamFieldGoals = awayTeamFieldGoals;
    }

    public int getAwayTeamThreePointFieldGoals() {
        return awayTeamThreePointFieldGoals;
    }

    public void setAwayTeamThreePointFieldGoals(int awayTeamThreePointFieldGoals) {
        this.awayTeamThreePointFieldGoals = awayTeamThreePointFieldGoals;
    }

    public int getAwayTeamFreeThrows() {
        return awayTeamFreeThrows;
    }

    public void setAwayTeamFreeThrows(int awayTeamFreeThrows) {
        this.awayTeamFreeThrows = awayTeamFreeThrows;
    }

    public int getHomeTeamRebounds() {
        return homeTeamRebounds;
    }

    public void setHomeTeamRebounds(int homeTeamRebounds) {
        this.homeTeamRebounds = homeTeamRebounds;
    }

    public int getAwayTeamRebounds() {
        return awayTeamRebounds;
    }

    public void setAwayTeamRebounds(int awayTeamRebounds) {
        this.awayTeamRebounds = awayTeamRebounds;
    }

    public int getHomeTeamTurnovers() {
        return homeTeamTurnovers;
    }

    public void setHomeTeamTurnovers(int homeTeamTurnovers) {
        this.homeTeamTurnovers = homeTeamTurnovers;
    }

    public int getAwayTeamTurnovers() {
        return awayTeamTurnovers;
    }

    public void setAwayTeamTurnovers(int awayTeamTurnovers) {
        this.awayTeamTurnovers = awayTeamTurnovers;
    }

    public int getHomeTeamPersonalFouls() {
        return homeTeamPersonalFouls;
    }

    public void setHomeTeamPersonalFouls(int homeTeamPersonalFouls) {
        this.homeTeamPersonalFouls = homeTeamPersonalFouls;
    }

    public int getAwayTeamPersonalFouls() {
        return awayTeamPersonalFouls;
    }

    public void setAwayTeamPersonalFouls(int awayTeamPersonalFouls) {
        this.awayTeamPersonalFouls = awayTeamPersonalFouls;
    }
}
