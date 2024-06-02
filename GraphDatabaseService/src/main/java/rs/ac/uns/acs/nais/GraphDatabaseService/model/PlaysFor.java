package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
public class PlaysFor {

    @RelationshipId
    private Long id;
    @TargetNode
    private Team team;

    private int jerseyNumber;
    private boolean isPlaying;
}
