package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.TeamPlayersOfGameCount;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TeamPlayersOfGameDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.WinnerStats;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WinnerStatsDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Team;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchResultRepository matchResultRepository;
    @Autowired
    private WinnerStatsRepository winnerStatsRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamPlayersOfGameCountRepository teamPlayersOfGameCountRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(long id) {
        return teamRepository.findById(id);
    }

    public void deleteById(long id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(long id, Team teamDetails) {
        return teamRepository.findById(id).map(team -> {
            team.setName(teamDetails.getName());
            team.setCity(teamDetails.getCity());
            team.setCountry(teamDetails.getCountry());
            team.setAddress(teamDetails.getAddress());
            team.setEmail(teamDetails.getEmail());
            team.setPhoneNumber(teamDetails.getPhoneNumber());
            team.setPlayersIds(teamDetails.getPlayersIds());
            return teamRepository.save(team);
        }).orElseThrow(() -> new RuntimeException("Team not found with id " + id));
    }

    public List<Team> getTeamsFromUSA(){
        return teamRepository.getTeamsFromUSA();
    }

    public List<TeamPlayersOfGameDto> getTeamsPlayersOfGameCount(){
        List<Long> playerOfGameIds = matchResultRepository.getPlayerOfGameIdsForLowScoringGames();

        List<TeamPlayersOfGameCount> teamsPlayerOfGameCount = teamPlayersOfGameCountRepository.countPlayersOfGameByTeams(playerOfGameIds);
        List<Long> teamsPlayerOfGameCountTeamsIds = new ArrayList<>();
        for (TeamPlayersOfGameCount teamPlayersOfGameCount:
             teamsPlayerOfGameCount) {
            teamsPlayerOfGameCountTeamsIds.add(teamPlayersOfGameCount.getTeam_id());
        }
        List<Team> teamsMoreInfo = teamRepository.getMoreInfoAboutTeams(teamsPlayerOfGameCountTeamsIds);

        List<TeamPlayersOfGameDto> finalList = new ArrayList<>();
        for (Team t:
             teamsMoreInfo) {
            for (TeamPlayersOfGameCount tc:
                 teamsPlayerOfGameCount) {
                if(t.getId() == tc.getTeam_id()) finalList.add(new TeamPlayersOfGameDto(t.getName(),t.getCountry(),t.getCity(),tc.getCount()));
            }
        }

        return finalList;
    }

    public List<TeamPlayersOfGameDto> getTeamsFromUSAPlayersOfGameCount(){
        List<Long> playerOfGameIds = teamRepository.getTeamsFromUSAIds();

        List<TeamPlayersOfGameCount> teamsPlayerOfGameCount = teamPlayersOfGameCountRepository.countPlayersOfGameByTeamsFromUSA(playerOfGameIds);
        List<Long> teamsPlayerOfGameCountTeamsIds = new ArrayList<>();
        for (TeamPlayersOfGameCount teamPlayersOfGameCount:
                teamsPlayerOfGameCount) {
            teamsPlayerOfGameCountTeamsIds.add(teamPlayersOfGameCount.getTeam_id());
        }
        List<Team> teamsMoreInfo = teamRepository.getMoreInfoAboutTeams(teamsPlayerOfGameCountTeamsIds);

        List<TeamPlayersOfGameDto> finalList = new ArrayList<>();
        for (Team t:
                teamsMoreInfo) {
            for (TeamPlayersOfGameCount tc:
                    teamsPlayerOfGameCount) {
                if(t.getId() == tc.getTeam_id()) finalList.add(new TeamPlayersOfGameDto(t.getName(),t.getCountry(),t.getCity(),tc.getCount()));
            }
        }

        return finalList;
    }
    public List<WinnerStatsDto> getHomeWinnerTeamsStats(){
        List<Long> homeTeamIds = matchRepository.getHomeTeamsIds();
        List<WinnerStats> winnerStats = winnerStatsRepository.getWinnerStats(homeTeamIds);

        List<Long> winnerTeamsIds = new ArrayList<>();
        for (WinnerStats ws:
             winnerStats) {
            winnerTeamsIds.add(ws.getWinner_id());
        }

        List<Team> teamsMoreInfo = teamRepository.getMoreInfoAboutTeams(winnerTeamsIds);
        List<WinnerStatsDto> finalList = new ArrayList<>();
        for (Team t:
             teamsMoreInfo) {
            for (WinnerStats ws:
                 winnerStats) {
                if(t.getId() == ws.getWinner_id()) finalList.add(new WinnerStatsDto(t.getName(),t.getCountry(),t.getCity(),ws.getAvgScore(),ws.getAvgRebounds(),ws.getAvgTurnovers()));
            }
        }

        return finalList;
    }

    public List<WinnerStatsDto> getWinnerTeamsFromUSAStats(){
        List<Long> usaTeamIds = teamRepository.getTeamsFromUSAIds();
        List<WinnerStats> winnerStats = winnerStatsRepository.getWinnerStats(usaTeamIds);

        List<Long> winnerTeamsIds = new ArrayList<>();
        for (WinnerStats ws:
                winnerStats) {
            winnerTeamsIds.add(ws.getWinner_id());
        }

        List<Team> teamsMoreInfo = teamRepository.getMoreInfoAboutTeams(winnerTeamsIds);
        List<WinnerStatsDto> finalList = new ArrayList<>();
        for (Team t:
                teamsMoreInfo) {
            for (WinnerStats ws:
                    winnerStats) {
                if(t.getId() == ws.getWinner_id()) finalList.add(new WinnerStatsDto(t.getName(),t.getCountry(),t.getCity(),ws.getAvgScore(),ws.getAvgRebounds(),ws.getAvgTurnovers()));
            }
        }

        return finalList;
    }

    public byte[] export() throws IOException {
        List<Team> teamsForReport = getTeamsFromUSA();
        List<Player> playersForReport = playerRepository.getPlayersFromUSA();
        List<TeamPlayersOfGameDto> teamsPlayersOfGame= getTeamsFromUSAPlayersOfGameCount();
        List<WinnerStatsDto> winnerStats = getWinnerTeamsFromUSAStats();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        com.lowagie.text.Document document = new com.lowagie.text.Document();

        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        //----------------------------------------------- prva sekcija ------------------------------------------

        Paragraph title = new Paragraph("USA TEAMS INFO REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = createReportTable(teamsForReport);
        document.add(reportTable);
        //----------------------------------------------- druga sekcija ------------------------------------------

        Paragraph newSectionTitle = new Paragraph("USA PLAYERS INFO REPORT", titleFont);
        newSectionTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(newSectionTitle);

        PdfPTable anotherReportTable = createPlayersReportTable(playersForReport);
        document.add(anotherReportTable);

        //--------------------------------------------- slozena sekcija 1 -----------------------------------------

        Paragraph newSectionTitle2 = new Paragraph("USA TEAMS PLAYERS OF GAME FROM USA COUNT", titleFont);
        newSectionTitle2.setAlignment(Element.ALIGN_CENTER);
        document.add(newSectionTitle2);

        PdfPTable anotherReportTable2 = createTeamsPlayersOfGameCountTable(teamsPlayersOfGame);
        document.add(anotherReportTable2);

        //--------------------------------------------- slozena sekcija 2 -----------------------------------------

        Paragraph newSectionTitle3 = new Paragraph("STATS", titleFont);
        newSectionTitle3.setAlignment(Element.ALIGN_CENTER);
        document.add(newSectionTitle3);

        PdfPTable anotherReportTable3 = createStatsTable(winnerStats);

        document.add(anotherReportTable3);

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private PdfPTable createStatsTable(List<WinnerStatsDto> winnerStats) {
        PdfPTable reportTable = new PdfPTable(6);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Team", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("City", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Country", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("Avg points", headerFont));
        PdfPCell headerCell5 = new PdfPCell(new Paragraph("Avg rebounds", headerFont));
        PdfPCell headerCell6 = new PdfPCell(new Paragraph("Avg turnovers", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell4.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell5.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell6.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);
        reportTable.addCell(headerCell4);
        reportTable.addCell(headerCell5);
        reportTable.addCell(headerCell6);

        for (WinnerStatsDto team : winnerStats) {
            reportTable.addCell(team.getName());
            reportTable.addCell(team.getCity());
            reportTable.addCell(team.getCountry());
            reportTable.addCell(String.valueOf(team.getAvgScore()));
            reportTable.addCell(String.valueOf(team.getAvgRebounds()));
            reportTable.addCell(String.valueOf(team.getAvgTurnovers()));
        }

        return reportTable;
    }

    private PdfPTable createTeamsPlayersOfGameCountTable(List<TeamPlayersOfGameDto> teamsPlayersOfGame) {
        PdfPTable reportTable = new PdfPTable(4);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Team", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("City", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Country", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("Num of players of the game from USA", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell4.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);
        reportTable.addCell(headerCell4);

        for (TeamPlayersOfGameDto team : teamsPlayersOfGame) {
            reportTable.addCell(team.getName());
            reportTable.addCell(team.getCity());
            reportTable.addCell(team.getCountry());
            reportTable.addCell(String.valueOf(team.getCountPlayerOfGame()));
        }

        return reportTable;
    }

    private PdfPTable createReportTable(List<Team> teamsForReport) {
        PdfPTable reportTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Team", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("City", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Country", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);

        for (Team team : teamsForReport) {
            reportTable.addCell(team.getName());
            reportTable.addCell(team.getCity());
            reportTable.addCell(team.getCountry());
        }

        return reportTable;
    }

    private PdfPTable createPlayersReportTable(List<Player> playersForReport) {
        PdfPTable reportTable = new PdfPTable(8);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Name", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Surname", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Birthday", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("City", headerFont));
        PdfPCell headerCell5 = new PdfPCell(new Paragraph("Country", headerFont));
        PdfPCell headerCell6 = new PdfPCell(new Paragraph("Height", headerFont));
        PdfPCell headerCell7 = new PdfPCell(new Paragraph("Weight", headerFont));
        PdfPCell headerCell8 = new PdfPCell(new Paragraph("Status", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell4.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell5.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell6.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell7.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell8.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);
        reportTable.addCell(headerCell4);
        reportTable.addCell(headerCell5);
        reportTable.addCell(headerCell6);
        reportTable.addCell(headerCell7);
        reportTable.addCell(headerCell8);

        for (Player player : playersForReport) {
            reportTable.addCell(player.getName());
            reportTable.addCell(player.getSurname());
            reportTable.addCell(player.getBirthday().toString());
            reportTable.addCell(player.getCity());
            reportTable.addCell(player.getCountry());
            reportTable.addCell(String.valueOf(player.getHeight()));
            reportTable.addCell(String.valueOf(player.getWeight()));
            reportTable.addCell(String.valueOf(player.getStatus()));
        }

        return reportTable;
    }
}
