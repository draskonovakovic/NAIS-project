package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaysMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;

import java.util.List;

public interface ITeamService {
    List<Team> findAll();
    Team addTeam(Team team);
    boolean deleteTeam(String email);
    boolean updateTeam(String emailOld, String emailNew);
    void addPlaysMatch(PlaysMatchDTO playsMatchDTO);
    void addMultiplePlaysMatch(List<PlaysMatchDTO> multiplePlaysMatchDTO);
    List<Team> recommendTeamsForReferee(Long refereeId);
}
