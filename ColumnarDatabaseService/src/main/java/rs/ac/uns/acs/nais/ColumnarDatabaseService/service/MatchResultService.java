package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchResult;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.MatchResultRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchResultService {
    @Autowired
    private MatchResultRepository matchResultRepository;

    public MatchResult saveMatchResult(MatchResult matchResult) {
        return matchResultRepository.save(matchResult);
    }

    public Optional<MatchResult> findMatchResultById(long id) {
        return matchResultRepository.findById(id);
    }

    public List<MatchResult> findAll() {
        return matchResultRepository.findAll();
    }

    public void deleteMatchResultById(long id) {
        matchResultRepository.deleteById(id);
    }

    public MatchResult updateMatchResult(long id, MatchResult matchResultDetails) {
        return matchResultRepository.findById(id).map(matchResult -> {
            matchResult.setMatchId(matchResultDetails.getMatchId());
            matchResult.setWinnerId(matchResultDetails.getWinnerId());
            matchResult.setHomeTeamScore(matchResultDetails.getHomeTeamScore());
            matchResult.setAwayTeamScore(matchResultDetails.getAwayTeamScore());
            matchResult.setPlayerOfGameId(matchResultDetails.getPlayerOfGameId());
            matchResult.setHomeTeamFieldGoals(matchResultDetails.getHomeTeamFieldGoals());
            matchResult.setHomeTeamThreePointFieldGoals(matchResultDetails.getHomeTeamThreePointFieldGoals());
            matchResult.setHomeTeamFreeThrows(matchResultDetails.getHomeTeamFreeThrows());
            matchResult.setAwayTeamFieldGoals(matchResultDetails.getAwayTeamFieldGoals());
            matchResult.setAwayTeamThreePointFieldGoals(matchResultDetails.getAwayTeamThreePointFieldGoals());
            matchResult.setAwayTeamFreeThrows(matchResultDetails.getAwayTeamFreeThrows());
            matchResult.setHomeTeamRebounds(matchResultDetails.getHomeTeamRebounds());
            matchResult.setAwayTeamRebounds(matchResultDetails.getAwayTeamRebounds());
            matchResult.setHomeTeamTurnovers(matchResultDetails.getHomeTeamTurnovers());
            matchResult.setAwayTeamTurnovers(matchResultDetails.getAwayTeamTurnovers());
            matchResult.setHomeTeamPersonalFouls(matchResultDetails.getHomeTeamPersonalFouls());
            matchResult.setAwayTeamPersonalFouls(matchResultDetails.getAwayTeamPersonalFouls());
            return matchResultRepository.save(matchResult);
        }).orElseThrow(() -> new RuntimeException("MatchResult not found with id " + id));
    }
}
