package com.example.events.player;

import lombok.Data;

import com.example.dto.PlayerDto;
import com.example.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
public class PlayerEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private final PlayerDto player;

    public PlayerEvent(PlayerDto player){
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

    public PlayerDto getPlayer() {
        return player;
    }
}
