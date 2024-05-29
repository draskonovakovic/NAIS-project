package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaysMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TeamRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITeamService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team addTeam(Team team) {
        team.setIsActive(true);
        return teamRepository.save(team);
    }

    @Override
    public boolean deleteTeam(String email) {
        var teamFromDb = teamRepository.findByEmail(email);
        if(teamFromDb != null){
            teamFromDb.setIsActive(false);
            teamRepository.save(teamFromDb);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeam(String emailOld, String emailNew) {
        var teamFromDb = teamRepository.findByEmail(emailOld);
        if(teamFromDb != null){
            teamFromDb.setEmail(emailNew);
            teamRepository.save(teamFromDb);
            return true;
        }
        return false;
    }

    @Override
    public void addPlaysMatch(PlaysMatchDTO playsMatchDTO) {
        teamRepository.createPlay(playsMatchDTO.getTeamId(), playsMatchDTO.getMatchId(), playsMatchDTO.getTeamSide());
    }

    @Override
    public void addMultiplePlaysMatch(List<PlaysMatchDTO> multiplePlaysMatchDTO) {
        for(var playsMatchDTO: multiplePlaysMatchDTO){
            teamRepository.createPlay(playsMatchDTO.getTeamId(), playsMatchDTO.getMatchId(), playsMatchDTO.getTeamSide());
        }
    }
}
