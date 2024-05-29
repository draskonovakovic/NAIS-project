package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Hall;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.HallService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hall")
public class HallController {

    private final HallService hallService;

    @GetMapping("getAll")
    public ResponseEntity<List<Hall>> getAll(){
        return new ResponseEntity<>(hallService.getAll(), HttpStatus.OK);
    }

    @PostMapping("addHalls")
    public ResponseEntity<List<Hall>> addHalls(@RequestBody List<Hall> halls){
        return new ResponseEntity<>(hallService.addHalls(halls), HttpStatus.OK);
    }

    @GetMapping("getRecommendationsForTeam")
    public ResponseEntity<List<Hall>> getRecommendationsForTeam(@RequestParam Long teamId){
        return new ResponseEntity<>(hallService.recommendHallsForTeam(teamId), HttpStatus.OK);
    }
}
