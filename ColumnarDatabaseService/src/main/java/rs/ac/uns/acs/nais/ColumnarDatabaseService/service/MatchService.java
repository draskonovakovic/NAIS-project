package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchesGroupByCity;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Match;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.MatchGroupByCityRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.MatchRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.RefereeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private RefereeRepository refereeRepository;
    @Autowired
    private MatchGroupByCityRepository matchGroupByCityRepository;

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Optional<Match> findMatchById(long id) {
        return matchRepository.findById(id);
    }

    public Match updateMatch(long id, Match matchDetails) {
        return matchRepository.findById(id).map(match -> {
            match.setMatchDateTime(matchDetails.getMatchDateTime());
            match.setHomeTeamId(matchDetails.getHomeTeamId());
            match.setAwayTeamId(matchDetails.getAwayTeamId());
            match.setCity(matchDetails.getCity());
            match.setMatchResultId(matchDetails.getMatchResultId());
            match.setRefereeId(matchDetails.getRefereeId());
            return matchRepository.save(match);
        }).orElseThrow(() -> new RuntimeException("Match not found with id " + id));
    }

    public void deleteMatchById(long id) {
        matchRepository.deleteById(id);
    }

    public List<MatchesGroupByCity> getMatchesInfoGroupedByCity(){
        List<Long> youngerRefereesIds = refereeRepository.getYoungerRefereesIds();

        List<MatchesGroupByCity> citiesMatchCount = matchGroupByCityRepository.groupMatchesByCity(youngerRefereesIds);

        return citiesMatchCount;
    }
}

