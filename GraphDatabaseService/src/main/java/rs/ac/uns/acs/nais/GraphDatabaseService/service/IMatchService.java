package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;

import java.util.List;

public interface IMatchService {
    List<Match> findAll();
    Match addMatch(Match match);
    boolean deleteMatch(Long id);
    boolean updateMatch(Long id, String cityOld, String cityNew);
}
