package rs.ac.uns.acs.nais.GraphDatabaseService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PlayerService;

import java.util.function.Function;

@Configuration
public class PlayerConfig {

    @Autowired
    private PlayerService playerService;

    @Bean
    public Function<Flux<PlayerEvent>, Flux<PlayerEvent>> graphPlayerProcessor(){
        return flux -> flux.flatMap(this::processPlayer);
    }

    private Mono<PlayerEvent> processPlayer(PlayerEvent event){
        var newPlayer = new Player();
        newPlayer.setId(event.getPlayer().getId());
        newPlayer.setName(event.getPlayer().getName());
        newPlayer.setSurname(event.getPlayer().getSurname());
        newPlayer.setHeight(event.getPlayer().getHeight());
        newPlayer.setJerseyNumber(event.getPlayer().getJerseyNumber());
        newPlayer.setEmail(event.getPlayer().getEmail());
        newPlayer.setIsActive(event.getPlayer().getIsActive());

        newPlayer = this.playerService.addPlayer(newPlayer);
        
        return Mono.fromSupplier(() -> event);
    }
}
