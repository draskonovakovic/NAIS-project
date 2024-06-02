package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
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

    @PrimaryKeyColumn(name = "id", ordinal = 0, ordering = Ordering.ASCENDING)
    private long id;

    @PrimaryKeyColumn(name = "match_date_time", ordinal = 1, ordering = Ordering.DESCENDING)
    private LocalDateTime matchDateTime;

    @Column("home_team_id")
    private long homeTeamId;

    @Column("away_team_id")
    private long awayTeamId;

    @PrimaryKeyColumn(name = "city", type = PrimaryKeyType.PARTITIONED)
    private String city;

    @Column("match_result_id")
    private long matchResultId;

    @Column("referee_id")
    private long refereeId;

    public long getId() {
        return id;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public long getHomeTeamId() {
        return homeTeamId;
    }

    public long getAwayTeamId() {
        return awayTeamId;
    }

    public String getCity() {
        return city;
    }

    public long getMatchResultId() {
        return matchResultId;
    }

    public long getRefereeId() {
        return refereeId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public void setHomeTeamId(long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public void setAwayTeamId(long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMatchResultId(long matchResultId) {
        this.matchResultId = matchResultId;
    }

    public void setRefereeId(long refereeId) {
        this.refereeId = refereeId;
    }
}
