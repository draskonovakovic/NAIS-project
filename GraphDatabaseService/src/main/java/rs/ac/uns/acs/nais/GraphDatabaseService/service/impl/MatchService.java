package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.MatchRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IMatchService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService implements IMatchService {
    
    private final MatchRepository matchRepository;

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match addMatch(Match match) {
        match.setIsActive(true);
        return matchRepository.save(match);
    }

    @Override
    public boolean deleteMatch(String email) {
        var matchFromDb = matchRepository.findByEmail(email);
        if(matchFromDb != null){
            matchFromDb.setIsActive(false);
            matchRepository.save(matchFromDb);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMatch(String emailOld, String emailNew) {
        var matchFromDb = matchRepository.findByEmail(emailOld);
        if(matchFromDb != null){
            matchFromDb.setEmail(emailNew);
            matchRepository.save(matchFromDb);
            return true;
        }
        return false;
    }
    
}
