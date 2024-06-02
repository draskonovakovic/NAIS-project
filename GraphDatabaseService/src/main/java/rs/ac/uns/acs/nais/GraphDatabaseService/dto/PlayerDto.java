package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDto {

    private Long id;
    private String name;
    private String surname;
    private Integer height;
    private Integer jerseyNumber;
    private String email;
    private Boolean isActive;

}
