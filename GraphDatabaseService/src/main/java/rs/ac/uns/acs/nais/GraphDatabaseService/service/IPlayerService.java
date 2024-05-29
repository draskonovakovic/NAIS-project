package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();
    Player addPlayer(Player player);
    boolean deletePlayer(String email);
    boolean updatePlayer(String emailOld, String emailNew);
}
