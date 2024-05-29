package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.ExperienceRequest;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.RefereedDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Referee;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.RefereeRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IRefereeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefereeService implements IRefereeService {

    private final RefereeRepository refereeRepository;

    @Override
    public List<Referee> findAll() {
        return refereeRepository.findAll();
    }

    @Override
    public Referee addReferee(Referee referee) {
        referee.setIsActive(true);
        return refereeRepository.save(referee);
    }

    @Override
    public boolean deleteReferee(String email) {
        var refereeFromDb = refereeRepository.findByEmail(email);
        if (refereeFromDb != null) {
            refereeFromDb.setIsActive(false);
            refereeRepository.save(refereeFromDb);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReferee(String emailOld, String emailNew) {
        var refereeFromDb = refereeRepository.findByEmail(emailOld);
        if (refereeFromDb != null) {
            refereeFromDb.setEmail(emailNew);
            refereeRepository.save(refereeFromDb);
            return true;
        }
        return false;
    }

    public void addRefereed(RefereedDTO refereedDTO){
        refereeRepository.createRefereed(refereedDTO.getRefereeId(), refereedDTO.getMatchId(), refereedDTO.getPoints(), refereedDTO.isRisk());
    }

    @Override
    public List<Referee> recommendRefereesByExperience(ExperienceRequest request) {
        return refereeRepository.recommendRefereeByExperience(request.getMatchId(), request.getMatchDay());

    }
}
