package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Player> createTeam(@RequestBody Player player) {
        Player savedTeam = playerService.save(player);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllTeams() {
        List<Player> teams = playerService.findAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getTeamById(@PathVariable long id) {
        Optional<Player> team = playerService.findById(id);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player playerDetails) {
        try {
            Player updatedPlayer = playerService.updatePlayer(id, playerDetails);
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
