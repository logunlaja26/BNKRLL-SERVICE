package com.bnkrll.service;

import com.bnkrll.model.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(String sessionID);
    void save(Session session);
    List<Session> getLastSessions(int numofSessions);
    void deleteById(String id);
}
