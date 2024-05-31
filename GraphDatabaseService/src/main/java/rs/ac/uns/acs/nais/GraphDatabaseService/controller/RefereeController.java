package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.ExperienceRequest;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.RefereedDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.RefereeService;

import java.sql.Ref;
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

    @PostMapping("addRefereed")
    public ResponseEntity addRefereed(@RequestBody RefereedDTO refereedDTO) {
        refereeService.addRefereed(refereedDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("recommendRefereesByExperience")
    public ResponseEntity<List<Referee>> recommendRefereesByExperience(@RequestBody ExperienceRequest request) {
        var retVal = refereeService.recommendRefereesByExperience(request);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping("recommendByAvgPoints")
    public ResponseEntity<List<Referee>> recommendByAvgPoints(@RequestBody ExperienceRequest request) {
        var retVal = refereeService.recommendRefereesByAvgPoints(request);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping("insertList")
    public ResponseEntity<List<Referee>> insertList(@RequestBody List<Referee> refereeList) {
        for(Referee referee: refereeList) {
            refereeService.addReferee(referee);
        }
        return new ResponseEntity<>(refereeList, HttpStatus.CREATED);
    }

    @PostMapping("insertListRefereed")
    public ResponseEntity<List<RefereedDTO>> insertListRefereed(@RequestBody List<RefereedDTO> request) {
        for(RefereedDTO refereedDTO: request) {
            refereeService.addRefereed(refereedDTO);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
