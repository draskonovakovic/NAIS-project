package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class PlaysMatch {

    @RelationshipId
    private Long id;
    @TargetNode
    private Match match;
    private String teamSide;
    private boolean won;
}
