package com.example.events.player;

import lombok.Data;

import com.example.dto.PlayerDto;
import com.example.events.Event;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
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

}
