package org.example.infrastructure.memory;

import org.example.domain.entity.Event;
import org.example.domain.entity.Participant;
import org.example.domain.spi.port.ParticipantRepository;
import org.example.domain.spi.port.ParticipantService;
import org.example.exception.NotFoundException;
import org.example.infrastructure.memory.entity.EventEntity;
import org.example.infrastructure.memory.entity.ParticipantEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantRepositoryInMemory implements ParticipantRepository {
    public Map<Integer, ParticipantEntity> participantEntityMap = new HashMap<>();
    private static int count = 0;
    @Override
    public void save(Participant participant) {
        ParticipantEntity participantEntity = ParticipantEntity.builder().id(count).name(participant.getName()).build();
        participantEntityMap.put(count++, participantEntity);
    }

    @Override
    public Participant findById(int id) throws NotFoundException {
        ParticipantEntity participantEntity = ParticipantEntity.builder().id(id).build();
        if(participantEntityMap.containsValue(participantEntity)){
            ParticipantEntity participantEntityFind = participantEntityMap.get(id);
            return new Participant(participantEntityFind.getId(),participantEntityFind.getName());
        }
        throw new NotFoundException();
    }

    @Override
    public List<Participant> findAll() {
        List<Participant> participantReturn = new ArrayList<>();
        for (ParticipantEntity p: this.participantEntityMap.values()) {
            participantReturn.add(new Participant(p.getId(),p.getName()));
        }
        return participantReturn;
    }
}
