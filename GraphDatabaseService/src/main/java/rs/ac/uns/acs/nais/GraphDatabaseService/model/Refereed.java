package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
public class Refereed {

    @RelationshipId
    private Long id;
    @TargetNode
    private Match match;
    private int points;
    private boolean isRisk;
}
