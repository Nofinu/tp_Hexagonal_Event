package org.example.adapter;

import org.example.domain.entity.Event;
import org.example.domain.entity.Participant;
import org.example.domain.service.EventServiceImpl;
import org.example.domain.service.ParticipantServiceImpl;
import org.example.domain.spi.port.EventService;
import org.example.domain.spi.port.ParticipantService;
import org.example.infrastructure.memory.EventRepositoryInMemoryImpl;
import org.example.infrastructure.memory.ParticipantRepositoryInMemory;

import java.util.Scanner;

public class EventAdapter {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final Scanner scanner;

    public EventAdapter() {
        this.participantService = new ParticipantServiceImpl(new ParticipantRepositoryInMemory());
        this.eventService = new EventServiceImpl(new EventRepositoryInMemoryImpl(),participantService);
        scanner = new Scanner(System.in);
    }

    public void start() {
        int entry = 0;
        do {
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    findByIdEvent();
                    break;
                case 3:
                    findAllUpcomingEvent();
                    break;
                case 4:
                  addParticipant();
                    break;
                case 5:
                    findAllParticipant();
                    break;
                case 6:
                    findByIdParticipant();
                    break;
                case 7:
                    inscription();
                    break;
                default:
                    break;
            }
        } while (entry != 0);
    }

    private void menu() {
        System.out.println("1-- cree un event");
        System.out.println("2-- afficher un event par son id");
        System.out.println("3-- afficher tout les events a venir");
        System.out.println("4-- cree un participant");
        System.out.println("5-- afficher tout les participant");
        System.out.println("6-- afficher un participant par sont id");
        System.out.println("7-- inscrire un participant a un event");
        System.out.println("0-- quitter");
    }

    private void addEvent() {
        System.out.println("creation d'un event :");
        System.out.println("title :");
        String title = scanner.nextLine();
        System.out.println("date format : dd/MM/yyyy");
        String dateStr = scanner.nextLine();
        System.out.println("Description :");
        String description = scanner.nextLine();

        eventService.createEvent(title,dateStr,description);
    }

    private void addParticipant (){
        System.out.println("creation d'un participant");
        System.out.println("nom :");
        String name = scanner.nextLine();

        participantService.create(name);
    }

    private void findByIdEvent(){
        System.out.println("trouver un event par son id :");
        System.out.println("id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Event event = eventService.findEventById(id);
        if(event != null){
            System.out.println(event);
        }else{
            System.out.println("event non trouvé");
        }
    }

    private void findByIdParticipant (){
        System.out.println("trouver un Participant par son id :");
        System.out.println("id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Participant participant = participantService.findById(id);
        if(participant != null){
            System.out.println(participant);
        }else{
            System.out.println("participant non trouvé");
        }
    }

    private void findAllParticipant (){
        System.out.println("tout les participants :");
        participantService.findAll().forEach(System.out::println);
    }

    private void findAllUpcomingEvent(){
        System.out.println("tout les events :");
        eventService.listUpcomingEvents().forEach(System.out::println);
    }

    private void inscription(){
        System.out.println("inscrire un participant a un event");
        System.out.println("id Event :");
        int idEvent = scanner.nextInt();
        System.out.println("id Participant :");
        int idParticipant = scanner.nextInt();
        scanner.nextLine();

        if(eventService.registerForEvent(idEvent,idParticipant)){
            System.out.println("participant inscrit");
        }else{
            System.out.println("erreur lors de l'inscription");
        }
    }

}
