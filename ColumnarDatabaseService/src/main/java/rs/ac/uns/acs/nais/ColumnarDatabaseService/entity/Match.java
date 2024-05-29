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

@Table("matches")
public class Match {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @Column("match_date_time")
    private LocalDateTime matchDateTime;

    @Column("home_team_id")
    private UUID homeTeamId;

    @Column("away_team_id")
    private UUID awayTeamId;

    @Column("city")
    private String city;

    @Column("match_result_id")
    private String matchResultId;

    @Column("referee_id")
    private String refereeId;
    public UUID getId() {
        return id;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public UUID getHomeTeamId() {
        return homeTeamId;
    }

    public UUID getAwayTeamId() {
        return awayTeamId;
    }

    public String getCity() {
        return city;
    }

    public String getMatchResultId() {
        return matchResultId;
    }

    public String getRefereeId() {
        return refereeId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public void setHomeTeamId(UUID homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public void setAwayTeamId(UUID awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMatchResultId(String matchResultId) {
        this.matchResultId = matchResultId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }
}
