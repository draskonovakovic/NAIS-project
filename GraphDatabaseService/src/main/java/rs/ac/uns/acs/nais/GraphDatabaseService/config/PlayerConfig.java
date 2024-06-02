package rs.ac.uns.acs.nais.GraphDatabaseService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PlayerService;

@Configuration
public class PlayerConfig {

    @Autowired
    private PlayerService playerService;
}
