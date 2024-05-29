package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefereedDTO {

    private Long matchId;
    private Long refereeId;
    private int points;
    private boolean isRisk;
}
