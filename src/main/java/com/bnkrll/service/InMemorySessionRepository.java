package com.bnkrll.service;

import com.bnkrll.exceptions.SessionNotFoundException;
import com.bnkrll.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

@Slf4j
@Repository
public class InMemorySessionRepository implements SessionRepository {

    private final Map<String, Session> data;

    public InMemorySessionRepository() {
        this.data = new HashMap<>();
    }

    @Override
    public Optional<Session> findById(String sessionID) {
        return Optional.ofNullable(this.data.get(sessionID));
    }

    @Override
    public void save(Session session) {
        this.data.put(session.getSessionId(), session);
        log.info("Session with id {} saved {}!", session.getSessionId(), session);
    }

    @Override
    public List<Session> getLastSessions(int numOfSessions) {
        return data.values().stream()
                .sorted(comparing(Session::getDate, reverseOrder()))
                .limit(numOfSessions)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        this.data.remove(id);
    }

    public void deleteAll() {
        data.clear();
    }
}
