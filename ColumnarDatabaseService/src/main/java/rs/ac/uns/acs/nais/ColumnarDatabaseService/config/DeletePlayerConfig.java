package rs.ac.uns.acs.nais.ColumnarDatabaseService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.events.player.GraphPlayerEvent;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.PlayerService;

import java.util.function.Consumer;

@Service
public class DeletePlayerConfig {

    @Autowired
    private PlayerService playerService;

    @Bean
    public Consumer<Flux<GraphPlayerEvent>> graphPlayerProcessor() {
        return flux -> flux.doOnNext(this::processPlayer).subscribe();
    }

    private void processPlayer(GraphPlayerEvent event) {
        var isActive = event.getPlayer().getIsActive();
        var playerId = event.getPlayer().getId();

        if (!isActive) {
            playerService.deleteById(playerId,0);
        }
    }
}
