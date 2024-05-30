package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(long id) {
        return playerRepository.findById(id);
    }

    public void deleteById(long id) {
        playerRepository.deleteById(id);
    }

    public Player updatePlayer(long id, Player playerDetails) {
        return playerRepository.findById(id).map(player -> {
            player.setEmail(playerDetails.getEmail());
            player.setName(playerDetails.getName());
            player.setSurname(playerDetails.getSurname());
            player.setBirthday(playerDetails.getBirthday());
            player.setCity(playerDetails.getCity());
            player.setCountry(playerDetails.getCountry());
            player.setHeight(playerDetails.getHeight());
            player.setWeight(playerDetails.getWeight());
            player.setStatus(playerDetails.getStatus());
            player.setJerseyNumber(playerDetails.getJerseyNumber());
            player.setTeamId(playerDetails.getTeamId());
            return playerRepository.save(player);
        }).orElseThrow(() -> new RuntimeException("Player not found with id " + id));
    }

    public List<Player> filterPlayersByHeightWeightAndBirthday(){
        return playerRepository.filterPlayersByHeightWeightAndBirthday();
    }
}
