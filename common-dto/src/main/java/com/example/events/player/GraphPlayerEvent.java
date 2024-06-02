package com.example.events.player;

import com.example.dto.PlayerDto;
import com.example.events.Event;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class GraphPlayerEvent implements Event {

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

}
