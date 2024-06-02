package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlayerDto {
    private Long id;
    private String name;
    private String surname;
    private double height;
    private int jerseyNumber;
    private String email;
    private Boolean isActive;
}
