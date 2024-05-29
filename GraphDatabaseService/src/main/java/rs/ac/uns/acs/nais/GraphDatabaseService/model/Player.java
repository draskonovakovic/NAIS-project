package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node
public class Player {
    @Id
    private Long id;

    private String name;
    private String surname;
    private Integer height;
    private Integer jerseyNumber;

    private String email;
    private Boolean isActive;
}
