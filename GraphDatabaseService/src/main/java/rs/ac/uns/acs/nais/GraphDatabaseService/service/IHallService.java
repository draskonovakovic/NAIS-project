package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Hall;

import java.util.List;

public interface IHallService {
    List<Hall> addHalls(List<Hall> halls);
    List<Hall> getAll();

    List<Hall> recommendHallsForTeam(Long teamId);

}
