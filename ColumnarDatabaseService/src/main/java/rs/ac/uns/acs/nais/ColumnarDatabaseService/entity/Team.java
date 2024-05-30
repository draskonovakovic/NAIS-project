package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;

import java.util.List;
import java.util.UUID;

@Table("teams")
public class Team {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private long id;

    @PrimaryKeyColumn(name = "name", ordinal = 0, ordering = Ordering.ASCENDING)
    private String name;

    @Column("city")
    private String city;

    @Column("country")
    private String country;

    @Column("address")
    private String address;

    @Column("email")
    private String email;

    @Column("phone_number")
    private String phoneNumber;

    @Column("players_ids")
    private List<Long> playersIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Long> getPlayersIds() {
        return playersIds;
    }

    public void setPlayersIds(List<Long> playersIds) {
        this.playersIds = playersIds;
    }
}