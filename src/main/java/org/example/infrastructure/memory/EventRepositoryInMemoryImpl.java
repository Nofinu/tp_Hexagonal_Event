package org.example.infrastructure.memory;

import org.example.domain.entity.Event;
import org.example.domain.spi.port.EventRepository;
import org.example.exception.NotFoundException;
import org.example.infrastructure.memory.entity.EventEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepositoryInMemoryImpl implements EventRepository {

    public Map<Integer, EventEntity> events = new HashMap<>();
    private static int count = 0;
    @Override
    public void save(Event event) {
        EventEntity eventEntity = EventEntity.builder().id(count).title(event.getTitle()).description(event.getDescription()).date(event.getDate()).participants(event.getParticipants()).build();
        events.put(count++, eventEntity);
    }

    @Override
    public void update (Event event){
        EventEntity eventEntity = EventEntity.builder().id(event.getId()).title(event.getTitle()).description(event.getDescription()).date(event.getDate()).participants(event.getParticipants()).build();
        events.put(event.getId(), eventEntity);
    }

    @Override
    public Event findById(int id) throws NotFoundException {
        EventEntity eventEntity = EventEntity.builder().id(id).build();
         if(events.containsValue(eventEntity)){
             EventEntity eventEntityFind = events.get(id);
             return new Event(eventEntityFind.getId(),eventEntityFind.getTitle(),eventEntityFind.getDate(),eventEntityFind.getDescription(),eventEntityFind.getParticipants());
         }
        throw new NotFoundException();
    }

    @Override
    public List<Event> findAll() {
        List<Event> eventReturn = new ArrayList<>();
        for (EventEntity e: this.events.values()) {
            eventReturn.add(new Event(e.getId(),e.getTitle(),e.getDate(),e.getDescription(),e.getParticipants()));
        }
        return eventReturn;
    }
}
