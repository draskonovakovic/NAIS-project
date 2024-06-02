package rs.ac.uns.acs.nais.GraphDatabaseService.events;

import java.util.Date;
import java.util.UUID;

public interface Event {

    UUID getEventId();
    Date getDate();

}
