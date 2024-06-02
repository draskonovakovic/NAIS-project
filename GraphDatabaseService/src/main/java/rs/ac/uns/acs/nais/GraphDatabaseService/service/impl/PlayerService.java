package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaysForDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PlayerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPlayerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        player.setIsActive(true);
        return playerRepository.save(player);
    }

    @Override
    public boolean deletePlayer(String email) {
        var playerFromDb = playerRepository.findByEmail(email);
        if(playerFromDb != null){
            playerFromDb.setIsActive(false);
            playerRepository.save(playerFromDb);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePlayer(String emailOld, String emailNew) {
        var playerFromDb = playerRepository.findByEmail(emailOld);
        if(playerFromDb != null){
            playerFromDb.setEmail(emailNew);
            playerRepository.save(playerFromDb);
            return true;
        }
        return false;
    }

    @Override
    public void addPlaysFor(PlaysForDTO playsForDTO) {
        playerRepository.setPlaysFor(playsForDTO.getPlayerId(), playsForDTO.getTeamId(), playsForDTO.getJerseyNumber());
    }
}
