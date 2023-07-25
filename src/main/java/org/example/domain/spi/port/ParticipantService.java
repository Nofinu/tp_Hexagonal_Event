package org.example.domain.spi.port;

import org.example.domain.entity.Participant;

import java.util.List;

public interface ParticipantService {
    void create (String name);

    Participant findById(int id);

    List<Participant> findAll();
}
