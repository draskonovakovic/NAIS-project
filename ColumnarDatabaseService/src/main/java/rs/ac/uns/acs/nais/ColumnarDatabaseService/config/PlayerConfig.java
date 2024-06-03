package rs.ac.uns.acs.nais.ColumnarDatabaseService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.PlayerDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.events.player.ColumnPlayerEvent;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.events.player.GraphPlayerEvent;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.PlayerService;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


@Configuration
public class PlayerConfig {
    @Bean
    public Sinks.Many<ColumnPlayerEvent> playerSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<ColumnPlayerEvent>> playerSupplier(Sinks.Many<ColumnPlayerEvent> sink){
        return sink::asFlux;
    }
}
