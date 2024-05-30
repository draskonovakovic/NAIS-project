package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.TeamPlayersOfGameCount;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TeamPlayersOfGameDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.WinnerStats;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WinnerStatsDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchResultRepository matchResultRepository;
    @Autowired
    private WinnerStatsRepository winnerStatsRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamPlayersOfGameCountRepository teamPlayersOfGameCountRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(long id) {
        return teamRepository.findById(id);
    }

    public void deleteById(long id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(long id, Team teamDetails) {
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

    public List<Team> getTeamsFromUSA(){
        return teamRepository.getTeamsFromUSA();
    }

    public List<TeamPlayersOfGameDto> getTeamsPlayersOfGameCount(){
        List<Long> playerOfGameIds = matchResultRepository.getPlayerOfGameIdsForLowScoringGames();

        List<TeamPlayersOfGameCount> teamsPlayerOfGameCount = teamPlayersOfGameCountRepository.countPlayersOfGameByTeams(playerOfGameIds);
        List<Long> teamsPlayerOfGameCountTeamsIds = new ArrayList<>();
        for (TeamPlayersOfGameCount teamPlayersOfGameCount:
             teamsPlayerOfGameCount) {
            teamsPlayerOfGameCountTeamsIds.add(teamPlayersOfGameCount.getTeam_id());
        }
        List<Team> teamsMoreInfo = teamRepository.getMoreInfoAboutTeams(teamsPlayerOfGameCountTeamsIds);

        List<TeamPlayersOfGameDto> finalList = new ArrayList<>();
        for (Team t:
             teamsMoreInfo) {
            for (TeamPlayersOfGameCount tc:
                 teamsPlayerOfGameCount) {
                if(t.getId() == tc.getTeam_id()) finalList.add(new TeamPlayersOfGameDto(t.getName(),t.getCountry(),t.getCity(),tc.getCount()));
            }
        }

        return finalList;
    }

    public List<WinnerStatsDto> getHomeWinnerTeamsStats(){
        List<Long> homeTeamIds = matchRepository.getHomeTeamsIds();
        List<WinnerStats> winnerStats = winnerStatsRepository.getWinnerStats(homeTeamIds);

        List<Long> winnerTeamsIds = new ArrayList<>();
        for (WinnerStats ws:
             winnerStats) {
            winnerTeamsIds.add(ws.getWinner_id());
        }

        List<Team> teamsMoreInfo = teamRepository.getMoreInfoAboutTeams(winnerTeamsIds);
        List<WinnerStatsDto> finalList = new ArrayList<>();
        for (Team t:
             teamsMoreInfo) {
            for (WinnerStats ws:
                 winnerStats) {
                if(t.getId() == ws.getWinner_id()) finalList.add(new WinnerStatsDto(t.getName(),t.getCountry(),t.getCity(),ws.getAvgScore(),ws.getAvgRebounds(),ws.getAvgTurnovers()));
            }
        }

        return finalList;
    }
}
