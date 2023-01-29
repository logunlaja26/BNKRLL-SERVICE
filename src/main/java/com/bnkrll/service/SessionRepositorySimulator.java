package com.bnkrll.service;

import com.bnkrll.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class SessionRepositorySimulator implements SessionRepository {

    private final Map<String, Session> data;

    public SessionRepositorySimulator() {
        this.data = new HashMap<>();
    }


    @Override
    public Session findById(String sessionID) {
        return this.data.get(sessionID);
    }

    @Override
    public void save(Session session) {
        this.data.put(session.getSessionId(), session);
        log.info("Session with id {} saved!", session.getSessionId());
    }

    @Override
    public List<Session> getLastSessions(int numOfSessions) {
        for (int i = 0; i < 30; i++) {
            save(createSessions());
        }
        List<Session> listOfSessions = new ArrayList<>(this.data.values());
        log.info("Amount of sessions created - {}", listOfSessions.size());
        int start = 0;
        int end = Math.min(30, numOfSessions);
        List<Session> sessions = listOfSessions.subList(start, end);
        this.data.clear();
        return sessions;
    }

    public Session createSessions() {
        return Session.builder().sessionId(UUID.randomUUID().toString()).build();
    }
}
