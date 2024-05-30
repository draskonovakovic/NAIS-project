package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TeamPlayersOfGameDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WinnerStatsDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.TeamService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamService.save(team);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.findAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable long id) {
        Optional<Team> team = teamService.findById(id);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable long id) {
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable long id, @RequestBody Team teamDetails) {
        try {
            Team updatedTeam = teamService.updateTeam(id, teamDetails);
            return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usa")
    public ResponseEntity<List<Team>> getTeamsFromUSA() {
        List<Team> teams = teamService.getTeamsFromUSA();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/firstComplexQuery")
    public ResponseEntity<List<TeamPlayersOfGameDto>> getTeamsPlayersOfGameCount(){
        List<TeamPlayersOfGameDto> responseList = teamService.getTeamsPlayersOfGameCount();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/get-teams-from-usa-players-of-game-count")
    public ResponseEntity<List<TeamPlayersOfGameDto>> getTeamsFromUSAPlayersOfGameCount(){
        List<TeamPlayersOfGameDto> responseList = teamService.getTeamsFromUSAPlayersOfGameCount();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/secondComplexQuery")
    public ResponseEntity<List<WinnerStatsDto>> getHomeWinnerTeamsStats(){
        List<WinnerStatsDto> responseList = teamService.getHomeWinnerTeamsStats();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/usa-teams-winner-stats")
    public ResponseEntity<List<WinnerStatsDto>> getHomeWinnerTeamsFromUSAStats(){
        List<WinnerStatsDto> responseList = teamService.getWinnerTeamsFromUSAStats();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {
        try {
            byte[] pdfContents = teamService.export();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "USA-teams.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContents);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
