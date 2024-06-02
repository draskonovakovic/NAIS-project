package rs.ac.uns.acs.nais.ColumnarDatabaseService.events.player;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.PlayerDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.events.Event;

import java.util.Date;
import java.util.UUID;

public class GraphPlayerEvent implements Event {
    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private final PlayerDto player;

    public GraphPlayerEvent(PlayerDto player) {
        this.player = player;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public PlayerDto getPlayer(){return this.player;}
}
