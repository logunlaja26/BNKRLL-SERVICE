package com.bnkrll.service;

import com.bnkrll.model.Session;
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
    public List<Session> getLastSessions(int numOfSessions) {
        Session sessionOne = Session.builder().sessionId(SESSION_ID_ONE).build();
        Session sessionTwo = Session.builder().sessionId(SESSION_ID_TWO).build();
        List<Session> listofSessions = new ArrayList<>(Arrays.asList(sessionOne,sessionTwo));
        this.data.put(sessionOne.getSessionId(),sessionOne);
        this.data.put(sessionTwo.getSessionId(),sessionTwo);
        return listofSessions;
    }
}
