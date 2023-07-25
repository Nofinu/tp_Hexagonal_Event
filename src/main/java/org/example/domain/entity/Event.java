package org.example.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Event {
    private int id;
    private String title;
    private LocalDate date;
    private String description;
    private List<Participant> participants =new ArrayList<>();

    public Event() {
    }

    public Event(int id, String title, LocalDate date, String description, List<Participant> participants) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.participants = participants;
    }

    public Event(String title, LocalDate date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public void addEntrant (Participant entrant){
        this.participants.add(entrant);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> entrants) {
        this.participants = entrants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", participants=" + participants +
                '}';
    }
}
