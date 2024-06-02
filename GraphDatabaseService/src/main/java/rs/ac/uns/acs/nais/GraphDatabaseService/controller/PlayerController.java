package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaysForDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PlayerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("getAll")
    public ResponseEntity<List<Player>> getAll(){
        var players = playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Player> create(@RequestBody Player player) {
        Player retVal = playerService.addPlayer(player);
        return new ResponseEntity<>(retVal, HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Player> delete(@RequestParam String playerEmail) {
        if(playerService.deletePlayer(playerEmail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateEmail")
    public ResponseEntity<Player> updateEmail(@RequestParam String oldEmail, @RequestParam String newEmail) {
        if(playerService.updatePlayer(oldEmail, newEmail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addPlaysFor")
    public ResponseEntity<Player> addPlaysFor(@RequestBody PlaysForDTO playsForDTO){
        playerService.addPlaysFor(playsForDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
