package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.ExperienceRequest;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaysMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.RefereedDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;

import java.util.List;

public interface IRefereeService {
    List<Referee> findAll();
    Referee addReferee(Referee referee);
    boolean deleteReferee(String email);
    boolean updateReferee(String emailOld, String emailNew);
    void addRefereed(RefereedDTO refereedDTO);
    List<Referee> recommendRefereesByExperience(ExperienceRequest request);
    List<Referee> recommendRefereesByAvgPoints(ExperienceRequest request);
    List<Referee> recommendRefereeForTeamByMatchesWon(Long teamId);
    List<Referee> recommendRefereesByAvgAttendaceOnMatch(Long matchId);
    void updateRefereed(Long refereeId, Long matchId, int points, boolean isRisk);
}
