package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExperienceRequest {
    private Long matchId;
    private LocalDateTime matchDay;
}
