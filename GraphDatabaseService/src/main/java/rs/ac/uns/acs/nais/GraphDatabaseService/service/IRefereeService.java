package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;

import java.util.List;

public interface IRefereeService {
    List<Referee> findAll();
    Referee addReferee(Referee referee);
    boolean deleteReferee(String email);
    boolean updateReferee(String emailOld, String emailNew);
}
