package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Referee;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.RefereeRepository;

import java.util.Optional;
import java.util.UUID;


@Service
public class RefereeService {

    @Autowired
    private RefereeRepository refereeRepository;

    public Referee saveReferee(Referee referee) {
        return refereeRepository.save(referee);
    }

    public Optional<Referee> findRefereeById(long id) {
        return refereeRepository.findById(id);
    }

    public Referee updateReferee(long id, Referee refereeDetails) {
        return refereeRepository.findById(id).map(referee -> {
            referee.setEmail(refereeDetails.getEmail());
            referee.setName(refereeDetails.getName());
            referee.setSurname(refereeDetails.getSurname());
            referee.setBirthday(refereeDetails.getBirthday());
            referee.setCity(refereeDetails.getCity());
            return refereeRepository.save(referee);
        }).orElseThrow(() -> new RuntimeException("Referee not found with id " + id));
    }

    public void deleteRefereeById(long id) {
        refereeRepository.deleteById(id);
    }
}
