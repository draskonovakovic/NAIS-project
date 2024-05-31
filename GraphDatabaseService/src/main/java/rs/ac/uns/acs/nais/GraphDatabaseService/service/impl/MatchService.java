package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.MatchRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.RefereeRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IMatchService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService implements IMatchService {
    
    private final MatchRepository matchRepository;
    private final RefereeRepository refereeRepository;

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match addMatch(Match match) {
        match.setIsActive(true);
        return matchRepository.save(match);
    }

    @Override
    public boolean deleteMatch(Long id) {
        var matchFromDb = matchRepository.findById(id).orElse(null);
        if(matchFromDb != null){
            matchFromDb.setIsActive(false);
            matchRepository.save(matchFromDb);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMatch(Long id, String emailOld, String emailNew) {
        var matchFromDb = matchRepository.findById(id).orElse(null);
        if(matchFromDb != null){
            matchFromDb.setCity(emailNew);
            matchRepository.save(matchFromDb);
            return true;
        }
        return false;
    }

    @Override
    public List<Match> addMatches(List<Match> matches) {
        return matchRepository.saveAll(matches);
    }
    
    public byte[] exportPdf(Long matchId) throws IOException {
        var matchFromDb = matchRepository.findById(matchId).orElse(null);

        assert matchFromDb != null;
        var recommendationForMatch = refereeRepository.recommendRefereeByExperience(matchFromDb.getId(), matchFromDb.getMatchDay());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        com.lowagie.text.Document document = new com.lowagie.text.Document();

        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("MATCH NUMBER No." + matchFromDb.getId(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Font matchDataFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL);
        Paragraph matchData = new Paragraph();
        matchData.setAlignment(Element.ALIGN_CENTER);
        matchData.add(new Chunk("City: " + matchFromDb.getCity() + "\n", matchDataFont));
        matchData.add(new Chunk("Date: " + matchFromDb.getMatchDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + "\n\n", matchDataFont));
        document.add(matchData);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("First name", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Last name", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Contact", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("Recommended role", headerFont));

        PdfPTable reportTable = new PdfPTable(4);
        reportTable.setWidthPercentage(90);

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);
        reportTable.addCell(headerCell4);

        int rank = 1;
        for(var referee: recommendationForMatch) {
            reportTable.addCell(referee.getName());
            reportTable.addCell(referee.getSurname());
            reportTable.addCell(referee.getEmail());
            reportTable.addCell(GetRole(rank));
            rank++;
        }

        document.add(reportTable);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private String GetRole(int rank) {
        return switch (rank) {
            case 1 -> "Main referee";
            case 2 -> "Second referee";
            case 3 -> "Third referee";
            case 4 -> "Fourth referee";
            default -> "Invalid Rank";
        };
    }
}
