package com.bnkrll.org.service;

import com.bnkrll.org.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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
}
