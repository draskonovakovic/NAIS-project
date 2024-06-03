package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchesGroupByCity;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Match;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.MatchService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}/{date}/{city}")
    public ResponseEntity<Match> findMatchById(@PathVariable long id, @PathVariable LocalDateTime date, @PathVariable String city) {
        Optional<Match> match = matchService.findMatchById(id,date,city);
        return match.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/{date}/{city}")
    public ResponseEntity<Match> updateMatch(@PathVariable long id, @PathVariable LocalDateTime date, @PathVariable String city, @RequestBody Match matchDetails) {
        try {
            Match updatedMatch = matchService.updateMatch(id,date,city, matchDetails);
            return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/{date}/{city}")
    public ResponseEntity<Void> deleteMatchById(@PathVariable long id, @PathVariable LocalDateTime date, @PathVariable String city) {
        matchService.deleteMatchById(id,date,city);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/thirdComplexQuery")
    public ResponseEntity<List<MatchesGroupByCity>> getHomeWinnerTeamsStats(){
        List<MatchesGroupByCity> responseList = matchService.getMatchesInfoGroupedByCity();
        return ResponseEntity.ok(responseList);
    }
}
