package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchResult;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.MatchResultService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/match-results")
public class MatchResultController {
    @Autowired
    private MatchResultService matchResultService;

    @PostMapping
    public ResponseEntity<MatchResult> saveMatchResult(@RequestBody MatchResult matchResult) {
        MatchResult savedMatchResult = matchResultService.saveMatchResult(matchResult);
        return new ResponseEntity<>(savedMatchResult, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResult> findMatchResultById(@PathVariable long id) {
        Optional<MatchResult> matchResult = matchResultService.findMatchResultById(id);
        return matchResult.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchResultById(@PathVariable long id) {
        matchResultService.deleteMatchResultById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<MatchResult>> getAllResults() {
        List<MatchResult> results = matchResultService.findAll();
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchResult> updateMatchResult(@PathVariable long id, @RequestBody MatchResult matchResultDetails) {
        try {
            MatchResult updatedMatchResult = matchResultService.updateMatchResult(id, matchResultDetails);
            return new ResponseEntity<>(updatedMatchResult, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
