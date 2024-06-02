package rs.ac.uns.acs.nais.GraphDatabaseService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlayerDto;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.player.ColumnPlayerEvent;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.player.GraphPlayerEvent;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PlayerService;

import java.util.function.Function;

@Configuration
public class PlayerConfig {

    @Autowired
    private PlayerService playerService;

    @Bean
    public Function<Flux<ColumnPlayerEvent>, Flux<GraphPlayerEvent>> graphPlayerProcessor(){
        return flux -> flux.flatMap(this::processPlayer);
    }

    private Mono<GraphPlayerEvent> processPlayer(ColumnPlayerEvent event){
        var newPlayer = DtoToPlayer(event.getPlayer());

        newPlayer = this.playerService.addPlayer(newPlayer);

        var graphPlayerEvent = new GraphPlayerEvent(this.PlayerToDto(newPlayer));

        //If player.isActive is false then insert failed, true then it succeeded
        return Mono.fromSupplier(() -> graphPlayerEvent);
    }


    private Player DtoToPlayer(PlayerDto playerDto){
        var newPlayer = new Player();

        newPlayer.setId(playerDto.getId());
        newPlayer.setName(playerDto.getName());
        newPlayer.setSurname(playerDto.getSurname());
        newPlayer.setHeight(playerDto.getHeight());
        newPlayer.setJerseyNumber(playerDto.getJerseyNumber());
        newPlayer.setEmail(playerDto.getEmail());
        newPlayer.setIsActive(playerDto.getIsActive());

        return newPlayer;
    }

    private PlayerDto PlayerToDto(Player player){
        var playerDto = new PlayerDto();

        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setSurname(player.getSurname());
        playerDto.setHeight(player.getHeight());
        playerDto.setJerseyNumber(player.getJerseyNumber());
        playerDto.setEmail(player.getEmail());
        playerDto.setIsActive(player.getIsActive());

        return playerDto;
    }
}
