package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.MatchService;

import java.io.IOException;
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

    @PostMapping("insertList")
    public ResponseEntity<List<Match>> insertList(@RequestBody List<Match> matchList) {
        for (Match match : matchList) {
            matchService.addMatch(match);
        }
        return new ResponseEntity<>(matchList, HttpStatus.CREATED);
    }

    @PostMapping("addMatches")
    public ResponseEntity<List<Match>> addMatches(@RequestBody List<Match> matches) {
        var retVal = matchService.addMatches(matches);
        return new ResponseEntity<>(retVal, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "generatePdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestParam Long matchId) throws IOException {
        try {
            byte[] pdfContent = matchService.exportPdf(matchId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "refereeRecommendation.pdf");

            return ResponseEntity.ok().headers(headers).body(pdfContent);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
