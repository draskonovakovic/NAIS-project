package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public void deleteById(UUID id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(UUID id, Team teamDetails) {
        return teamRepository.findById(id).map(team -> {
            team.setName(teamDetails.getName());
            team.setCity(teamDetails.getCity());
            team.setCountry(teamDetails.getCountry());
            team.setAddress(teamDetails.getAddress());
            team.setEmail(teamDetails.getEmail());
            team.setPhoneNumber(teamDetails.getPhoneNumber());
            team.setPlayersIds(teamDetails.getPlayersIds());
            return teamRepository.save(team);
        }).orElseThrow(() -> new RuntimeException("Team not found with id " + id));
    }
}
