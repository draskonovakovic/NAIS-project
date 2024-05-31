package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Hall;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.HallRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IHallService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService implements IHallService {

    private final HallRepository hallRepository;

    @Override
    public List<Hall> addHalls(List<Hall> halls) {
        return hallRepository.saveAll(halls);
    }

    @Override
    public List<Hall> getAll() {
        return hallRepository.findAll();
    }

    @Override
    public List<Hall> recommendHallsForTeam(Long teamId) {
        return hallRepository.recommendHallsForTeam(teamId);
    }

    @Override
    public void deleteAll() {
        hallRepository.deleteAll();
    }
}
