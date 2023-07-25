package org.example.domain.spi.port;

import org.example.domain.entity.Event;
import org.example.exception.NotFoundException;

import java.util.List;

public interface EventRepository {
    void save (Event event);
    void update(Event event);
    Event findById (int id) throws NotFoundException;
    List<Event> findAll();


}
