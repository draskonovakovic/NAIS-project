package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Referee;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.RefereeService;

import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/referees")
public class RefereeController {

    @Autowired
    private RefereeService refereeService;

    @PostMapping
    public ResponseEntity<Referee> saveReferee(@RequestBody Referee referee) {
        Referee savedReferee = refereeService.saveReferee(referee);
        return new ResponseEntity<>(savedReferee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Referee> findRefereeById(@PathVariable long id) {
        Optional<Referee> referee = refereeService.findRefereeById(id);
        return referee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Referee> updateReferee(@PathVariable long id, @RequestBody Referee refereeDetails) {
        try {
            Referee updatedReferee = refereeService.updateReferee(id, refereeDetails);
            return new ResponseEntity<>(updatedReferee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefereeById(@PathVariable long id) {
        refereeService.deleteRefereeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

