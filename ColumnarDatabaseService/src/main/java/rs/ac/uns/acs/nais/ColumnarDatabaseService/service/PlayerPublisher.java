package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.PlayerDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Player;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.events.player.ColumnPlayerEvent;

@Service
public class PlayerPublisher {

    @Autowired
    private Sinks.Many<ColumnPlayerEvent> playerSink;

    public void raisePlayerEvent(final Player player){
        var dto = PlayerDto.of(
                player.getId(),
                player.getName(),
                player.getSurname(),
                player.getHeight(),
                player.getJerseyNumber(),
                player.getEmail(),
                true
        );
        var playerEvent = new ColumnPlayerEvent(dto);
        this.playerSink.tryEmitNext(playerEvent);
    }
}
