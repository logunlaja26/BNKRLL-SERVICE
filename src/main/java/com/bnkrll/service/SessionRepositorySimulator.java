package com.bnkrll.service;

import com.bnkrll.model.Session;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class SessionRepositorySimulator implements SessionRepository{

    private final Map<String, Session> data;

    private static final String SESSION_ID_ONE = "bb791404-78f0-11ed-a1eb-0242ac120002";
    private static final String SESSION_ID_TWO = "ada8881e-78f0-11ed-a1eb-0242ac120002";

    public SessionRepositorySimulator() {
        this.data = new HashMap<>();
    }


    @Override
    public Session findById(String sessionID) {
        return this.data.get(sessionID);
    }

    @Override
    public void save(Session session) {
        this.data.put(session.getSessionId(),session);
        log.info("Session with id {} saved!", session.getSessionId());
    }

    @Override
    public List<Session> getLastSessions(int numofSessions) {
        for(int i = 0; i < numofSessions; i++){
            this.data.put(createSessions().getSessionId(),createSessions());
        }
        List<Session> listofSessions = new ArrayList<>(this.data.values());
        log.info("Amount of sessions created - {}", listofSessions.size());
        this.data.clear();
        return listofSessions;
    }

    public Session createSessions(){
        return Session.builder().sessionId(UUID.randomUUID().toString()).build();
    }
}
