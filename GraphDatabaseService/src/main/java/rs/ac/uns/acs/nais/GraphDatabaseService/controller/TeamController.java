package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaysMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.TeamService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {
    
    private final TeamService teamService;

    @GetMapping("getAll")
    public ResponseEntity<List<Team>> getAll(){
        var Teams = teamService.findAll();
        return new ResponseEntity<>(Teams, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Team> create(@RequestBody Team team) {
        Team retVal = teamService.addTeam(team);
        return new ResponseEntity<>(retVal, HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Team> delete(@RequestParam String teamEmail) {
        if(teamService.deleteTeam(teamEmail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateEmail")
    public ResponseEntity<Team> updateEmail(@RequestParam String oldEmail, @RequestParam String newEmail) {
        if(teamService.updateTeam(oldEmail, newEmail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addPlaysMatch")
    public ResponseEntity addPlaysMatch(@RequestBody PlaysMatchDTO playsMatchDTO) {
        teamService.addPlaysMatch(playsMatchDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("addMultiplePlaysMatch")
    public ResponseEntity addMultiplePlaysMatch(@RequestBody List<PlaysMatchDTO> multiplePlaysMatchDTO) {
        teamService.addMultiplePlaysMatch(multiplePlaysMatchDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
