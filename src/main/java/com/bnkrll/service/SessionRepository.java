package com.bnkrll.service;

import com.bnkrll.model.Session;

import java.util.List;

public interface SessionRepository {
    Session findById(String sessionID);
    void save(Session session);
    List<Session> getLastSessions(int numofSessions);
    void deleteById(String id);
}
