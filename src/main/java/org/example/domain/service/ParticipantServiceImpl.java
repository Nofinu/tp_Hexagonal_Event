package org.example.domain.service;

import org.example.domain.entity.Participant;
import org.example.domain.spi.port.ParticipantRepository;
import org.example.domain.spi.port.ParticipantService;
import org.example.exception.NotFoundException;

import java.util.List;

public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public void create(String name) {
        Participant participant = new Participant(name);
        participantRepository.save(participant);
    }

    @Override
    public Participant findById(int id) {
        try{
            return participantRepository.findById(id);
        }catch (NotFoundException ex){
            return null;
        }
    }

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }
}
