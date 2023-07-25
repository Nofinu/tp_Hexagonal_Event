package org.example.domain.spi.port;

import org.example.domain.entity.Event;
import org.example.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface EventService {

    void createEvent(String title, String date,String description);
    boolean registerForEvent(int eventid,int participantId);

    List<Event> listUpcomingEvents();

    Event findEventById(int id);

}
