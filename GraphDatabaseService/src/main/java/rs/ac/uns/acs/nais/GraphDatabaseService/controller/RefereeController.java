package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.RefereeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/referee")
public class RefereeController {

    private final RefereeService refereeService;

    @GetMapping("getAll")
    public ResponseEntity<List<Referee>> getAll() {
     List<Referee> referees = refereeService.findAll();
        return new ResponseEntity<>(referees, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Referee> create(@RequestBody Referee referee) {
        Referee retVal = refereeService.addReferee(referee);
        return new ResponseEntity<>(retVal, HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Referee> delete(@RequestParam String refereeEmail) {
        if(refereeService.deleteReferee(refereeEmail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateEmail")
    public ResponseEntity<Referee> updateEmail(@RequestParam String oldEmail, @RequestParam String newEmail) {
        if(refereeService.updateReferee(oldEmail, newEmail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
