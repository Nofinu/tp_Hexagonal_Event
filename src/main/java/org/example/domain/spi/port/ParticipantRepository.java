package org.example.domain.spi.port;

import org.example.domain.entity.Participant;
import org.example.exception.NotFoundException;

import java.util.List;

public interface ParticipantRepository {
    void save (Participant participant);
    Participant findById(int id) throws NotFoundException;
    List<Participant> findAll();
}
