package rs.ac.uns.acs.nais.ColumnarDatabaseService.events.player;

import lombok.Data;

import lombok.RequiredArgsConstructor;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.PlayerDto;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
public class ColumnPlayerEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private final PlayerDto player;

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public ColumnPlayerEvent(PlayerDto player) {
        this.player = player;
    }
}
