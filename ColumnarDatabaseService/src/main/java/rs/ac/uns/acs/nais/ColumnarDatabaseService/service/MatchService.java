package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Match;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.MatchRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Optional<Match> findMatchById(UUID id) {
        return matchRepository.findById(id);
    }

    public Match updateMatch(UUID id, Match matchDetails) {
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

    public void deleteMatchById(UUID id) {
        matchRepository.deleteById(id);
    }
}

