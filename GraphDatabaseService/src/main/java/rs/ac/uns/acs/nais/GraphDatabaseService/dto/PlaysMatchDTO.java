package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import lombok.Data;

@Data
public class PlaysMatchDTO {
    private Long matchId;
    private Long teamId;
    private String teamSide;
    private Boolean won;
}
