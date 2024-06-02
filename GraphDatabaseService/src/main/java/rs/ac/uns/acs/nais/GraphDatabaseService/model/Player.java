package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Node
public class Player {
    @Id
    private Long id;

    private String name;
    private String surname;
    private Integer height;
    private Integer jerseyNumber;

    @Relationship(value = "PLAYS_FOR", direction = Relationship.Direction.OUTGOING)
    private PlaysFor playsFor;

    private String email;
    private Boolean isActive;
}
