package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node
public class Team {
    @Id
    private Long id;

    private String name;
    private String country;
    private String city;

    @Relationship(value = "HAS_PLAYER", direction = Relationship.Direction.OUTGOING)
    private List<HasPlayer> players = new ArrayList<>();

    @Relationship(value = "PLAYS_MATCH", direction = Relationship.Direction.OUTGOING)
    private List<PlaysMatch> matches = new ArrayList<>();

    private String email;
    private Boolean isActive;
}
