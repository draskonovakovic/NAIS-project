package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Match;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.MatchService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    public ResponseEntity<Match> saveMatch(@RequestBody Match match) {
        Match savedMatch = matchService.saveMatch(match);
        return new ResponseEntity<>(savedMatch, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> findMatchById(@PathVariable UUID id) {
        Optional<Match> match = matchService.findMatchById(id);
        return match.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable UUID id, @RequestBody Match matchDetails) {
        try {
            Match updatedMatch = matchService.updateMatch(id, matchDetails);
            return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchById(@PathVariable UUID id) {
        matchService.deleteMatchById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
