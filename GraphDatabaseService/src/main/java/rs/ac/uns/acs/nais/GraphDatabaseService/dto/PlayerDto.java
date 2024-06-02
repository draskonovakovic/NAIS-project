package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
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
