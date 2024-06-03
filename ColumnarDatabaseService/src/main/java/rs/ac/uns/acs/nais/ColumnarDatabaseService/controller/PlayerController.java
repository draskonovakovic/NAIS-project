package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.PlayerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player savedTeam = playerService.save(player);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> teams = playerService.findAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}/{teamId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable long id,@PathVariable long teamId) {
        Optional<Player> team = playerService.findById(id,teamId);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/{teamId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable long id,@PathVariable long teamId) {
        playerService.deleteById(id,teamId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/{teamId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id,@PathVariable long teamId, @RequestBody Player playerDetails) {
        try {
            Player updatedPlayer = playerService.updatePlayer(id,teamId, playerDetails);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("filtered-players")
    public ResponseEntity<List<Player>> filterPlayersByHeightWeightAndBirthday() {
        List<Player> teams = playerService.filterPlayersByHeightWeightAndBirthday();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("usa")
    public ResponseEntity<List<Player>> getPlayersFromUSA() {
        List<Player> teams = playerService.getPlayersFromUSA();
        return ResponseEntity.ok(teams);
    }
}
