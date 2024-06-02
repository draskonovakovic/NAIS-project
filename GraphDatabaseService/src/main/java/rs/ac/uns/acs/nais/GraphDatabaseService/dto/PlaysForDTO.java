package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import lombok.Data;

@Data
public class PlaysForDTO {

    private Long playerId;
    private Long teamId;

    private int jerseyNumber;
    private boolean isPlaying;
}
