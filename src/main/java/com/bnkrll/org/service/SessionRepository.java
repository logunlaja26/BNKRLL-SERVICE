package com.bnkrll.org.service;

import com.bnkrll.org.model.Session;


public interface SessionRepository {

    Session findById(String sessionID);
    void save(Session session);
}
