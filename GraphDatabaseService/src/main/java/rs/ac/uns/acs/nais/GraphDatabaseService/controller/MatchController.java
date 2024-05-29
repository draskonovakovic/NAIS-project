package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.MatchService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @GetMapping("getAll")
    public ResponseEntity<List<Match>> getAll(){
        var matches = matchService.findAll();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Match> create(@RequestBody Match match) {
        Match retVal = matchService.addMatch(match);
        return new ResponseEntity<>(retVal, HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Match> delete(@RequestParam Long id) {
        if(matchService.deleteMatch(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateEmail")
    public ResponseEntity<Match> updateEmail(@RequestParam Long id, @RequestParam String oldCity, @RequestParam String newCity) {
        if(matchService.updateMatch(id, oldCity, newCity)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
