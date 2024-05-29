package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Node
public class Match {
    @Id
    private Long id;

    private LocalDateTime matchDay;
    private String city;

    @Relationship(value = "PLAYS_MATCH", direction = Relationship.Direction.OUTGOING)
    private List<PlaysMatch> teams = new ArrayList<>();

    private String email;
    private Boolean isActive;
}
