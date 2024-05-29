package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;

import java.util.List;

public interface IMatchService {
    List<Match> findAll();
    Match addMatch(Match match);
    boolean deleteMatch(String email);
    boolean updateMatch(String emailOld, String emailNew);
}
