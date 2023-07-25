package org.example.domain.service;


import org.example.domain.entity.Event;
import org.example.domain.entity.Participant;
import org.example.domain.spi.port.EventRepository;
import org.example.domain.spi.port.EventService;
import org.example.domain.spi.port.ParticipantService;
import org.example.exception.NotFoundException;
import org.example.infrastructure.memory.entity.EventEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ParticipantService participantService;
    public EventServiceImpl(EventRepository eventRepository,ParticipantService participantService) {
        this.eventRepository = eventRepository;
        this.participantService = participantService;
    }

    @Override
    public void createEvent(String title, String date, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Event event = new Event(title, LocalDate.parse(date,formatter),description);
        eventRepository.save(event);
    }

    @Override
    public boolean registerForEvent(int eventid, int participantId) {
        try{
            Event event = eventRepository.findById(eventid);
            Participant participant = participantService.findById(participantId);
            if(event != null && participant != null){
                event.addEntrant(participant);
                eventRepository.update(event);
                return true;
            }
            return false;
        }catch (NotFoundException ex){
            return false;
        }
    }

    @Override
    public List<Event> listUpcomingEvents() {
        return eventRepository.findAll().stream().filter(e -> e.getDate().isAfter(LocalDate.now())).toList();
    }

    @Override
    public Event findEventById(int id)  {
        try{
            return eventRepository.findById(id);
        }catch (NotFoundException ex){
            return null;
        }

    }
}
